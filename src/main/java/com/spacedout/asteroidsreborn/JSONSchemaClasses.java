package com.spacedout.asteroidsreborn;

import java.util.ArrayList;

public class JSONSchemaClasses {
	public class Asteroid{
		public int x;
		public int y;
		public int mass;
	}

	public class Blockade{
		public int x;
		public int y;
		public int rows;
		public int cols;
	}

	public class Planet{
		public int x;
		public int y;
		public int mass;
	}

	public class Enemy{
		public int x;
		public int y;
		public int dx;
		public int dy;
	}

	public class Stage {
		public Asteroid asteroid;
		public Blockade blockade;
		public Planet planet;
		public ArrayList<Enemy> enemies;
	}


}
