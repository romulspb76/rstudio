/*
 * ShinyViewerType.java
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
package org.rstudio.studio.client.shiny.model;

import com.google.gwt.core.client.JavaScriptObject;

public class ShinyViewerType extends JavaScriptObject
{
   protected ShinyViewerType() {}
   
   public final static int SHINY_VIEWER_USER = 0;
   public final static int SHINY_VIEWER_NONE = 1;
   public final static int SHINY_VIEWER_PANE = 2;
   public final static int SHINY_VIEWER_WINDOW = 3;
   public final static int SHINY_VIEWER_BROWSER = 4;
   
   public final native int getViewerType() /*-{ 
      return this.viewerType;
   }-*/;
}
