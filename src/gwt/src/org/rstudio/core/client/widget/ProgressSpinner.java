/*
 * ProgressSpinner.java
 *
 * Copyright (C) 2009-17 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.core.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import org.rstudio.core.client.Debug;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.Context2d.LineCap;
import com.google.gwt.core.client.Scheduler;

public class ProgressSpinner extends Composite
{
   public ProgressSpinner(int color)
   {
      // compute sizes
      outerRadius_ = (COORD_SIZE / 2) - 10;
      innerRadius_ = (outerRadius_ / 2) + 5;
      colorType_ = color;

      // create canvas host
      canvas_ = Canvas.createIfSupported();
      if (canvas_ == null)
      {
         Debug.log("Can't create progress spinner (no HTML5 canvas support)");
         initWidget(new HTMLPanel(""));
         return;
      }
      
      initWidget(canvas_);
      
      // initialize canvas
      canvas_.setCoordinateSpaceWidth(COORD_SIZE);
      canvas_.setCoordinateSpaceHeight(COORD_SIZE);

      // perform initial draw and start animation
      redraw();
      Scheduler.get().scheduleFixedPeriod(new Scheduler.RepeatingCommand()
      {
         @Override
         public boolean execute()
         {
            frame_++;
            redraw();
            return !complete_;
         }
      }, FRAME_RATE_MS);
   }

   public boolean isSupported()
   {
      return canvas_ != null;
   }
   
   public void detach()
   {
      complete_ = true;
   }

   public void setColorType(int color)
   {
      colorType_ = color;
   }
   
   private void redraw()
   {
      String color = colorType_ == COLOR_WHITE ? "255, 255, 255" : "0, 0, 0";

      Context2d ctx = canvas_.getContext2d();
      double center = COORD_SIZE / 2;
      // clear canvas (we draw with an alpha channel so otherwise would stack)
      ctx.clearRect(0, 0, COORD_SIZE, COORD_SIZE);
      for (int i = 0; i < NUM_BLADES; i++)
      {
         // compute angle for this blade
         double theta = ((2 * Math.PI) / NUM_BLADES) * i;
         double sin = Math.sin(theta);
         double cos = Math.cos(theta);

         // set line drawing context
         ctx.beginPath();
         ctx.setLineWidth(BLADE_WIDTH);
         ctx.setLineCap(LineCap.ROUND);

         // compute transparency for this blade
         double alpha = 1.0 - (((double)((i + frame_) % NUM_BLADES)) / 
                               ((double)NUM_BLADES));
         ctx.setStrokeStyle("rgba(" + color + ", " + alpha + ")");
         
         // draw the blade
         ctx.moveTo(center + sin * innerRadius_,
                    center + cos * innerRadius_);
         ctx.lineTo(center + sin * outerRadius_, 
                    center + cos * outerRadius_);
         ctx.stroke();
      }
   }
   
   // drawing parameters 
   private final static int NUM_BLADES    = 12;
   private final static int BLADE_WIDTH   = 9;
   private final static int COORD_SIZE    = 100;
   private final static int FRAME_RATE_MS = 75;
   
   public final static int COLOR_WHITE = 0;
   public final static int COLOR_BLACK = 1;
   
   private final int innerRadius_;
   private final int outerRadius_;
   private final Canvas canvas_;
   private int colorType_;

   private int frame_ = 0;
   private boolean complete_ = false;
}
