package com.spacedout.asteroidsreborn.game_objects;

import com.spacedout.asteroidsreborn.AsteroidsRebornApplication;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Background extends GameObject {
	protected Player player;
	protected backgroundStar[] stars;

	public Background(int x, int y, int width, int height, GraphicsContext gc, Player player, int numberOfStars, int mass) {
		super(x, y, width, height, 0, gc, mass);
		this.player = player;

		Canvas canvas = gc.getCanvas();

		Random random = new Random();

		stars = new backgroundStar[numberOfStars];
		System.out.println(random.nextInt((int) canvas.getWidth()));

		for (int i = 0; i < numberOfStars; i++) {

			int depthFromPlayer = AsteroidsRebornApplication.generateBiasedRandom(0.65, 1, 10);

			this.stars[i] = new backgroundStar(
					random.nextInt((int) canvas.getWidth()),
					random.nextInt((int) canvas.getHeight()),
					depthFromPlayer,
					player,
					gc,
					this.generateStarColour(depthFromPlayer)
			);
		}
	}

	//	protected File[] imageFiles = new File[32];
//	protected ArrayList<Image> loadedImages;
//
//	protected double viewWidthIU, viewHeightIU; // View width/height in Image Units (how many chunks of 2048px can be fit into the viewport)
//
//	public Background(int x, int y, int width, int height, String image, GraphicsContext gc, Player player) {
//		super(x, y, width, height, image, gc);
//		this.player = player;
//
//
//		// store the paths to all 32 images in imagePaths, images will then be loaded on demand
//		try {
//			File[] allFiles = new File(getClass().getResource("/images/starmap-sections").toURI()).listFiles();
//			for (int i = 0; i < allFiles.length; i++) {
//				// check if file matches the regex
//				if (allFiles[i].getName().matches("starmap-section-[0-9][0-9].png")) {
//					imageFiles[i] = allFiles[i];
//				}
//			}
//		} catch (URISyntaxException e) {
//			e.printStackTrace();
//		}
//
//		Canvas canvas = gc.getCanvas();
//
//		viewWidthIU = canvas.getWidth()/2048;
//		viewHeightIU = canvas.getHeight()/2048;
//
//		// figure out which images to render by converting universe coordinates into chunk numbers
//		// if (col > 7.0): wrap to col-7.0 (e.g 0.9).    if (col < 0.0): wrap to 7.0-col.
//		// if (row > 3.0): wrap to row-3.0.    if (row < 0.0): wrap to 3.0-row.
//
//		double imageCol = (this.x/2048d)-1;
//		double imageRow = (this.y/2048d)-1;
//
//		imageCol = (imageCol > 7.0) ? imageCol-7 : imageCol;
//		imageRow = (imageRow > 3.0) ? imageRow-3 : imageRow;
//
//		double minImageCol = imageCol-(viewWidthIU/2);
//		double maxImageCol = imageCol+(viewWidthIU/2);
//
//		double minImageRow = imageRow-(viewHeightIU/2);
//		double maxImageRow = imageRow+(viewHeightIU/2);
//
//		for (File imageFile : imageFiles) {
//			// use regex to select the 2 digits representing the row and column in the filename
//			Matcher matcher = Pattern.compile("[0-9][0-9]").matcher(imageFile.getName());
//			int[] col_row_coords = Stream.of(matcher.group().split(""))
//					.mapToInt(Integer::parseInt)
//					.toArray();
//
//			if ((col_row_coords[0] > minImageCol) && (col_row_coords[0] < maxImageCol)) {
//				if ((col_row_coords[1] > minImageRow) && (col_row_coords[1] < maxImageRow))	{
//
//					loadedImages.add(new backgroundImageSection(imageFile.getName(), imageCol*2048));
//
//				}
//			}
//
//		}
//	}


	@Override
	public void draw() {
		for (backgroundStar star : this.stars) {
			star.draw();
		}
	}

	@Override
	public void update() {
		// don't need to apply acceleration buffer here as it is already applied to the player

		for (backgroundStar star : this.stars) {
			star.update();
		}

	}

	private Color generateStarColour(int depthFromPlayer) {

//		Random random = new Random();
//
//		return new Color(
//				random.nextDouble(0, (255/255d)),
//				random.nextDouble(0, (50/255d)),
//				random.nextDouble(0, 1),
//				1
//		);

		return new Color(1, 1, 1, 1);

	}
}
