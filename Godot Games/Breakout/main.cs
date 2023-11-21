using Godot;
using System;

public partial class main : Node2D
{
	// Called when the node enters the scene tree for the first time.
	public override void _Ready()
	{
		//hides the reset button
		GetNode<Button>("Button").Hide();
	}

	// Called every frame. 'delta' is the elapsed time since the previous frame.
	public override void _Process(double delta)
	{
	}
	//gives the option to retry and also removes the ball
	public void _on_area_2d_body_entered(Node2D body)
	{
		GetNode<Button>("Button").Show();
		GetNode<ball>("Ball").QueueFree();
	}
	//reset button logic to reset the scene
	public void _on_button_pressed()
	{
		GetTree().ReloadCurrentScene();
	}
}
