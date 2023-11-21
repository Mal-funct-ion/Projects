using Godot;
using System;

public partial class ball : RigidBody2D
{
	// Called when the node enters the scene tree for the first time.
	public override void _Ready()
	{
	}

	// Called every frame. 'delta' is the elapsed time since the previous frame.
	public override void _Process(double delta)
	{
		//logic to break brick if the ball collides with it
		Godot.Collections.Array<Node2D> bodies = GetCollidingBodies();
		foreach (var body in bodies)
		{
			if (body.IsInGroup("Bricks"))
			{
				body.QueueFree();
			}
		}

	}
}
