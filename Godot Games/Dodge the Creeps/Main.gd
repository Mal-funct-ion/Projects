extends Node

@export var mob_scene: PackedScene
var score


# Called when the node enters the scene tree for the first time.
func _ready():
	#hides HUD on start up to give way to the disclaimer
	$HUD.hide()


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass

# function for when the game is over
func game_over():
	# Music stops
	$Music.stop()
	# Death sound plays
	$DeathSound.play()
	#Scoring stops
	$ScoreTimer.stop()
	#Mob spawning stops
	$MobTimer.stop()
	#HUD displays the game over screen on the HUD
	$HUD.show_game_over()

# starts up a new game
func new_game():
	# resets the score
	score = 0
	# music starts up again
	$Music.play()
	# updates score to show the proper score
	$HUD.update_score(score)
	#Displays message on the HUD
	$HUD.show_message("Get Ready")
	#Calls player's start function at the position of Start Position
	$Player.start($StartPosition.position)
	# Starts the timer for start timer
	$StartTimer.start()
	# Clears all exisiting mobs 
	get_tree().call_group("mobs", "queue_free")

# Whenever the mob timer completes a cycle this function calls
func _on_mob_timer_timeout():
	# makes a mob instance
	var mob = mob_scene.instantiate()
	# sets up the mob spawn location
	var mob_spawn_location = get_node("MobPath/MobSpawnLocation")
	mob_spawn_location.progress_ratio = randf()
	
	#direction faces towards the player
	var direction = mob_spawn_location.rotation + PI / 2
	
	#mobs actual position placed
	mob.position = mob_spawn_location.position
	
	#rotates the mob to go in a direction towards the player
	direction += randf_range(-PI/4, PI/4)
	mob.rotation = direction
	
	#Picks a random velocity to determine how fast it moves
	var velocity = Vector2(randf_range(150.0, 250.0), 0.0)
	mob.linear_velocity = velocity.rotated(direction)
	#places the mob onto the main scene
	add_child(mob)

# calls when the score timer goes off every second
func _on_score_timer_timeout():
	#increases and updates the score
	score += 1
	$HUD.update_score(score)

#calls when the start timer goes off
func _on_start_timer_timeout():
	#starts the mob and scoring timers
	$MobTimer.start()
	$ScoreTimer.start()

#calls when the showHUD signal from the disclaimer scene is emitted
func _on_disclaimer_show_hud():
	#shows the default HUD
	$HUD.show()
