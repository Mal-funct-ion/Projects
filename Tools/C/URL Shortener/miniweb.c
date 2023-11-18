#include <stdio.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <pthread.h>
#include "queue.h"
#include "base64.h"


void serveRequest(int fd) {
  // Read the request
  char buffer[1024];
	int bytesRead = read(fd,buffer,1023);
  buffer[bytesRead] = '\0';

  // Grab the method and URL
  char method[16];
  char url[128];
  sscanf(buffer,"%s %s",method,url);

  //If-else statement checks whether or not receiving a post or get request
  if(strcmp(method, "POST") == 0){
    //establish variables needed for process
    char* encodedURL;
    char decodedURL[256];
    int currentposition;
    char urlCode[6];
    //find location of the url within the buffer
    encodedURL = strstr(buffer, "\r\n\r\nurl=");
    //decode the URL to remove any hexidecimal character codes
    decodeURL(encodedURL+8, decodedURL);
    FILE* urlfp;
    //open the urls txt file 
    urlfp = fopen("urls.txt", "a");
    //find the current location where to write
    currentposition = (int)ftell(urlfp);
    //write a new line after each decoded URL 
    strcat(decodedURL, "\n");
    //write the URL to the file
    fwrite(decodedURL, strlen(decodedURL) , 1, urlfp);
    //encode the position of the start of the URL
    encode(currentposition, urlCode);
    //prepare to send a response to client
    FILE* f200 = fopen("200Response.txt", "r");
    //reuse buffer to send response to client
    fread(buffer,1023,1,f200);
    fclose(f200);
    //locates placeholder for the URLCode
    char* urlLocation = strstr(buffer, "XXXXXX");
    //replace placeholder with the URL Code
    for(int n = 0;n < 6;n++)
      *(urlLocation + n) = urlCode[n];
    //Send response to client
    write(fd, buffer, sizeof(buffer));
    fclose(urlfp);
  }else{
    //checks if the user is inputting a valid link
    if(strncmp(url,"/s/",3) == 0) {
      //decodes the URL
      int urlLocation = decode(url+3);
      //opens the url txt file for reading
      FILE* urls = fopen("urls.txt","r");
      //sets the position in the file to the value of the code
      int filePosition = fseek(urls, urlLocation, SEEK_SET);
      //gets that URL from the file
      char* finalURL = fgets(url, 128, urls);
      //prepares to send a response to the client to link them to the original url
      const char* responseStatus = "HTTP/1.1 301 Permanently Moved\r\n";
      const char* responseOther = "Location: ";
      char responseBuffer[1024];
      strcpy(responseBuffer,responseStatus);
      strcat(responseBuffer,responseOther);
      strcat(responseBuffer,finalURL);
      strcat(responseBuffer,"\r\n\r\n");
      // Send the response
      write(fd,responseBuffer,strlen(responseBuffer));
    } else {
      //404 response read from a file
      int f404 = open("404Response.txt",O_RDONLY);
      int readSize = read(f404,buffer,1023);
      close(f404);
      write(fd,buffer,readSize);
    }

  }
  close(fd);
}

//All code past this point was provided 
void* workerThread(void *arg) {
  queue* q = (queue*) arg;
  while(1) {
    int fd = dequeue(q);
    serveRequest(fd);
  }
  return NULL;
}

int fromHex(char ch) {
  if(ch >= '0' && ch <= '9')
    return (int) ch - '0';
  return (int) ch - 'A' + 10;
}

void decodeURL(char* src,char* dest) {
  while(*src != '\0') {
    if(*src == '%') {
      ++src;
      int n1 = fromHex(*src++);
      int n2 = fromHex(*src++);
      *dest++ = (char) n1*16+n2;
    } else {
      *dest++ = *src++;
    }
  }
  *dest = '\0';
}

int main() {
  // Set up the queue
  queue* q = queueCreate();

  // Set up the worker threads
  pthread_t w1,w2;
  pthread_create(&w1,NULL,workerThread,q);
  pthread_create(&w2,NULL,workerThread,q);

  // Create the socket
  int server_socket = socket(AF_INET , SOCK_STREAM , 0);
  if (server_socket == -1) {
    printf("Could not create socket.\n");
    return 1;
  }

  // Set the 'reuse address' socket option
  int on = 1;
  setsockopt(server_socket, SOL_SOCKET, SO_REUSEADDR, &on, sizeof(on));

  //Prepare the sockaddr_in structure
  struct sockaddr_in server;
  server.sin_family = AF_INET;
  server.sin_addr.s_addr = INADDR_ANY;
  server.sin_port = htons( 8888 );

  // Bind to the port we want to use
  if(bind(server_socket,(struct sockaddr *)&server , sizeof(server)) < 0) {
    printf("Bind failed\n");
    return 1;
  }
  printf("Bind done\n");

  // Mark the socket as a passive socket
  listen(server_socket , 3);

  // Accept incoming connections
  printf("Waiting for incoming connections...\n");
  while(1) {
    struct sockaddr_in client;
    int new_socket , c = sizeof(struct sockaddr_in);
    new_socket = accept(server_socket, (struct sockaddr *) &client, (socklen_t*)&c);
    if(new_socket != -1) {
      enqueue(q,new_socket);
    }
  }

  return 0;
}
