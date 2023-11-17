extends Area2D

signal hit

@export var speed = 400
var screen_size

# Called when the node enters the scene tree for the first time.
func _ready():
	# This sets up the boundary we want to stay within while playing the game
	screen_size = get_viewport_rect().size
	# Hides the player on startup since we have a start message as well as a disclaimer
	hide()


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	# Movement script
	var velocity = Vector2.ZERO
	if Input.is_action_pressed("move_up"):
		velocity.y -= 1
	if Input.is_action_pressed("move_down"):
		velocity.y += 1
	if Input.is_action_pressed("move_right"):
		velocity.x += 1
	if Input.is_action_pressed("move_left"):
		velocity.x -= 1
	#Checks if we're moving
	if velocity.length() > 0:
		#Makes sure that we aren't faster moving diagonally than if we're moving horizontally/vertically
		velocity = velocity.normalized() * speed
		# Plays the player's animation
		$AnimatedSprite2D.play()
	else:
		#Stops the animation when we're not moving
		$AnimatedSprite2D.stop()
	
	# updates the players position (multiplied by delta to move smoothly even when FPS varies)
	position += velocity * delta
	# locks player's movement to within the screen
	position = position.clamp(Vector2.ZERO, screen_size)
	
	# sets which animation to play
	if velocity.x != 0:
		# normal walking animation
		$AnimatedSprite2D.animation = "walk"
		$AnimatedSprite2D.flip_v = false
		# if x is negative then walk to the left if it's positive to the right
		$AnimatedSprite2D.flip_h = velocity.x < 0
	elif velocity.y != 0:
		# normal going up animation
		$AnimatedSprite2D.animation = "up"
		# if y is negative then going up (since negative is upwards) and vice versa
		$AnimatedSprite2D.flip_v = velocity.y > 0


#When an entity (mob) enters the player character
func _on_body_entered(body):
	# hides the player scene
	hide()
	# sends out a signal 
	hit.emit()
	#prevents the collision from going off too many times
	$CollisionShape2D.set_deferred("disabled", true)

func start(pos):
	# sets position
	position = pos
	# reveals player scene
	show()
	# enale collission
	$CollisionShape2D.disabled = false
