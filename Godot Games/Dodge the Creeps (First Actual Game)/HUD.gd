extends CanvasLayer

signal start_game

# function to show a message on the HUD
func show_message(text):
	$Message.text = text
	$Message.show()
	$MessageTimer.start()

#function that calls when the game is over
func show_game_over():
	show_message("Game Over")
	#waits for signal to occur before continuing to next line
	await $MessageTimer.timeout
	
	$Message.text = "Dodge the\nCreeps!"
	$Message.show()
	# resets the HUD to show the start button
	await get_tree().create_timer(1.0).timeout
	$StartButon.show()

#function to update score 
func update_score(score):
	$ScoreLabel.text = str(score)

# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass

#Hides message after a certain amount of time has passed
func _on_message_timer_timeout():
	$Message.hide()

#starts the game by emitting a signal
func _on_start_buton_pressed():
	$StartButon.hide()
	start_game.emit()
