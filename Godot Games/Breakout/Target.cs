using Godot;
using System;


public partial class Target : StaticBody2D
{
	// Called when the node enters the scene tree for the first time.


	Random rand = new Random();


	public override void _Ready()
	{
		//Brick gets a random color for a bit of variety
		GetNode<Polygon2D>("Polygon2D").Color = new Color((float)rand.NextDouble(), (float)rand.NextDouble(), (float)rand.NextDouble());
	}
	// Called every frame. 'delta' is the elapsed time since the previous frame.
	public override void _Process(double delta)
	{
	}
}


