package org.graphstream.ui.javafx.renderer.shape.javafx.shapePart;

import org.graphstream.ui.graphicGraph.GraphicElement;
import org.graphstream.ui.graphicGraph.stylesheet.Style;
import org.graphstream.ui.graphicGraph.stylesheet.StyleConstants;
import org.graphstream.ui.javafx.Backend;
import org.graphstream.ui.javafx.FxDefaultCamera;
import org.graphstream.ui.javafx.renderer.shape.javafx.ShapePaint;
import org.graphstream.ui.javafx.renderer.shape.javafx.ShapePaint.ShapeAreaPaint;
import org.graphstream.ui.javafx.renderer.shape.javafx.ShapePaint.ShapeColorPaint;
import org.graphstream.ui.javafx.renderer.shape.javafx.ShapePaint.ShapePlainColorPaint;
import org.graphstream.ui.javafx.renderer.shape.javafx.baseShapes.Form;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;

public class Fillable {
	
	/** The fill paint. */
	private ShapePaint fillPaint = null;
 
	/** Value in [0..1] for dyn-colors. */
	private double theFillPercent = 0.0;
	
	private Color theFillColor = null;
	
	private boolean plainFast = false;
	
	/** Fill the shape.
	 * @param g The Java2D graphics.
	 * @param dynColor The value between 0 and 1 allowing to know the dynamic plain color, if any.
	 * @param shape The shape to fill. */
	public void fill(GraphicsContext g, double dynColor, Color optColor, Shape shape, FxDefaultCamera camera) {
		if(plainFast) {
			g.setStroke(theFillColor);
			g.setFill(theFillColor);
			((Form)shape).drawByPoints(g, false);
	    } 
		else {
			if ( fillPaint instanceof ShapeAreaPaint ) {	
				Paint p = ((ShapeAreaPaint)fillPaint).paint(shape, camera.getMetrics().ratioPx2Gu) ;
				g.setFill(p);
				g.setStroke(p);
				((Form)shape).drawByPoints(g, false);
			}
			else if (fillPaint instanceof ShapeColorPaint ) {
				Paint p = ((ShapeColorPaint)fillPaint).paint(dynColor, optColor);
				g.setFill(p);
				g.setStroke(p);
				((Form)shape).drawByPoints(g, false);
			}
	    }
	}
	
	/** Fill the shape.
	 * @param g The Java2D graphics.
	 * @param shape The shape to fill. */
 	public void fill(GraphicsContext g, Shape shape, FxDefaultCamera camera) {
 		fill( g, theFillPercent, theFillColor, shape, camera );
 	}

    /** Configure all static parts needed to fill the shape. */
 	public void configureFillableForGroup(Backend bck, Style style, FxDefaultCamera camera ) {
 		fillPaint = ShapePaint.apply(style);
 
 		if(fillPaint instanceof ShapePlainColorPaint) {
 			ShapePlainColorPaint paint = (ShapePlainColorPaint)fillPaint;
 			
 		    plainFast = true;
 		    theFillColor = paint.color;
 		    bck.graphics2D().setStroke(theFillColor);
 		    bck.graphics2D().setFill(theFillColor);
 		    // We prepare to accelerate the filling process if we know the color is not dynamic
 		    // and is plain: no need to change the paint at each new position for the shape.
 		} 
 		else {
 		    plainFast = false;
 		}
 	}
 	
    /** Configure the dynamic parts needed to fill the shape. */
  	public void configureFillableForElement( Style style, FxDefaultCamera camera, GraphicElement element ) {
  	  	if( style.getFillMode() == StyleConstants.FillMode.DYN_PLAIN && element != null ) {
  	  		if ( element.getAttribute( "ui.color" ) instanceof Number ) {
  	  			theFillPercent = (float)((Number)element.getAttribute( "ui.color" ));
  	  			theFillColor = null;
  	  		}
  	  		else if ( element.getAttribute( "ui.color" ) instanceof Color ) {
  	  			theFillColor = ((Color)element.getAttribute( "ui.color" )); 
  	  			theFillPercent = 0;
  	  		}
  	  		else {
  	  			theFillPercent = 0; 
  	  			theFillColor = null;
  	  		}
  	  	}
  	  	else {
  	  		theFillPercent = 0;
  	  	}
  	}
}