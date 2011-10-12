/*
 * Copyright 2011 Jeffrey Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.engineeringnotebook.graphmanagerservice.graph;

import java.util.logging.Logger;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


// TODO: Auto-generated Javadoc
/**
 * The Vertex Class.
 */
@XmlRootElement(name = "vertex")
/**
 *
 * @author Jeffrey Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org
 */
public class Vertex {
  
  /** The label. */
  private String label;
  
  /** The id. */
  private String id;
  
  /** The Constant logger. */
  private static final Logger logger = Logger.getLogger(Vertex.class.getName());
  
  /**
   * Sets the label.
   *
   * @param vertexLabel the new label
   */
  public void setLabel(String vertexLabel) {
    this.label = vertexLabel;
  }
  
  /**
   * Sets the iD.
   *
   * @param vertexID the new iD
   */
  public void setID(String vertexID) {
    this.id = vertexID;
  }
  
  /**
   * Gets the label.
   *
   * @return the label
   */
  @XmlElement
  public String getLabel() {
    return label;
  }
  
  /**
   * Gets the iD.
   *
   * @return the iD
   */
  @XmlElement
  public String getID() {
    return id;
  }
  
}

