package com.javaproject.models;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.javaproject.exceptions.ResourceNotLoadedException;
import com.javaproject.interfaces.IDrawable;
import com.javaproject.interfaces.IObjectData;

public abstract class DrawableObject implements IDrawable {

	private final IObjectData data;
	
	protected final int Width, Height;
	protected int PosX, PosY;
	
	protected BufferedImage image;

	public DrawableObject(int _width, int _height, int _posX, int _posY, IObjectData _data) throws ResourceNotLoadedException {
		super();
		Width = _width;
		Height = _height;
		PosX = _posX;
		PosY = _posY;
		data = _data;

		loadImage();
	}

	public IObjectData getData() {
		return data;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(resize(image, Width, Height), PosX, PosY, null);
	}

	private void loadImage() throws ResourceNotLoadedException {
		try {
			URL url = getClass().getResource(data.getImgPathRelative());
			image = ImageIO.read(url);
		} catch (IOException e) {
			throw new ResourceNotLoadedException(data.getImgPathRelative());
		}
	}

	public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	} 
	
}
