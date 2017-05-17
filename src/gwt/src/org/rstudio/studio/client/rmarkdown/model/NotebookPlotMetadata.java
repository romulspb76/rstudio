/*
 * NotebookPlotMetadata.java
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
package org.rstudio.studio.client.rmarkdown.model;

import org.rstudio.core.client.js.JsArrayEx;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class NotebookPlotMetadata extends JavaScriptObject
{
   protected NotebookPlotMetadata()
   {
   }
   
   public final native int getHeight() /*-{
      return this.height || 0;
   }-*/;
   
   public final native int getWidth() /*-{
      return this.width || 0;
   }-*/;
   
   public final native JsArray<JsArrayEx> getConditions() /*-{
      return this.conditions || [];
   }-*/;
}
