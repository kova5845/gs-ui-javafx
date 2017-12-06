package org.graphstream.ui.fx.renderer.shape.fx.baseShapes;

import org.graphstream.ui.fx.Backend;
import org.graphstream.ui.fx.FxDefaultCamera;
import org.graphstream.ui.fx.renderer.Skeleton;
import org.graphstream.ui.fx.renderer.shape.fx.ShowCubics;
import org.graphstream.ui.fx.renderer.shape.fx.baseShapes.Form.Path2D;
import org.graphstream.ui.graphicGraph.GraphicElement;

import javafx.scene.canvas.GraphicsContext;

public class PolylineEdgeShape extends LineConnectorShape {
	public ShowCubics showCubics ;
	protected Path2D theShape = new Path2D();

	public PolylineEdgeShape() {
		this.showCubics = new ShowCubics();
	}

	@Override
	public void make(Backend backend, FxDefaultCamera camera) {
		int n = skel.size();
		
		theShape.reset();
		theShape.moveTo(skel.apply(0).x, skel.apply(0).y);
		
		for(int i = 0 ; i < n ; i++) {
			theShape.lineTo(skel.apply(i).x, skel.apply(i).y);
		}		
	}

	@Override
	public void makeShadow(Backend backend, FxDefaultCamera camera) {}

	@Override
	public void render(Backend bck, FxDefaultCamera camera, GraphicElement element, Skeleton skeleton) {
		GraphicsContext g = bck.graphics2D();
		make(bck, camera);
		strokableLine.stroke(g, theShape);
		fillableLine.fill(g, theSize, theShape);
		decorable.decorConnector(bck, camera, skel.iconAndText, element, theShape);
	}

	@Override
	public void renderShadow(Backend bck, FxDefaultCamera camera, GraphicElement element, Skeleton skeleton) {
		makeShadow(bck, camera);
 		shadowableLine.cast(bck.graphics2D(), theShape);			
	}

}