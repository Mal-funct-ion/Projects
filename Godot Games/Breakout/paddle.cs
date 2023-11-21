using Godot;
using System.ComponentModel;
using System.Numerics;
using Vector2 = Godot.Vector2;

public partial class paddle : CharacterBody2D
{
	// Called when the node enters the scene tree for the first time.

	[Export]
	public int Speed { get; set; } = 400;
	public Vector2 ScreenSize;
	public override void _Ready()
	{
		ScreenSize = GetViewportRect().Size;
	}

	// Called every frame. 'delta' is the elapsed time since the previous frame.
	public override void _Process(double delta)
	{
		//movement for the paddle 
		Vector2 velocity = Vector2.Zero;
		if (Input.IsActionPressed("move_right"))
		{
			velocity.X += 970;
		}
		if (Input.IsActionPressed("move_left"))
		{
			velocity.X -= 970;
		}
		Position += velocity * (float)delta;
		//locks the paddle to within the screen borders
		Position = new Vector2(
			x: Mathf.Clamp(Position.X, 70, ScreenSize.X - 70),
			y: Mathf.Clamp(Position.Y, 0, ScreenSize.Y)
		);

	}

}
