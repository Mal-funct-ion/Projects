extends CanvasLayer

signal showHUD 

# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(delta):
	pass

# function when the next button pressed
func _on_button_pressed():
	#removes the disclaimer scene from the main scene
	queue_free()
	#emits the signal to show the HUD
	showHUD.emit()
