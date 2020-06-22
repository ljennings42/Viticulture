package GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

public class ImageLoader {
	private static HashMap<Object, BufferedImage> cache = new HashMap<>();
	//Overload for maximum overdrive
	public synchronized static BufferedImage getImage(URL u) {
		BufferedImage img = (BufferedImage) cache.get(u);
		if(img == null) {
			img = loadImage(u);
			cache.put(u,img);
		}
		return img;
	}

	//Overload for maximum overdrive
	public synchronized static BufferedImage getImage(String f) {
		BufferedImage img = (BufferedImage) cache.get(f);
		if(img == null) {
			img = loadImage(f);
			cache.put(f,img);
		}
		return img;
	}


	private static BufferedImage loadImage(String imageName) {
		java.awt.Image origImage = Toolkit.getDefaultToolkit().getImage(
				imageName);
		return loadImage(origImage);
	}

	private static BufferedImage loadImage(URL imageURL) {
		java.awt.Image origImage = Toolkit.getDefaultToolkit().getImage(
				imageURL);
		return loadImage(origImage);
	}

	
	private static BufferedImage loadImage(Image origImage) {
		// Java normally loads images in a background thread.
		// This waits for the image to finish loading.
		try {
			MediaTracker tracker = new MediaTracker(new Panel());
			tracker.addImage(origImage, 0);
			tracker.waitForID(0);
			if(tracker.statusID(0, true) != MediaTracker.COMPLETE) {
				throw new RuntimeException("Unable to load image");
			}
		}catch(InterruptedException e) {
			//won't be interrupted
		}
		//If image loaded then create a BufferedImage which is modifiable
		int imageWidth = origImage.getWidth(null);
		int imageHeight = origImage.getHeight(null);

		BufferedImage buf = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_ARGB);
		//why do we draw here
		Graphics g = buf.createGraphics();//creates graphics2D used to draw image
		g.drawImage(origImage, 0,0,null);
		return buf;
	}

	
}
