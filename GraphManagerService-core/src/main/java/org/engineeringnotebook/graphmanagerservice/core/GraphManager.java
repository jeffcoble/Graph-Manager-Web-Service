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
package org.engineeringnotebook.graphmanagerservice.core;

import org.engineeringnotebook.graphmanagerservice.graph.Vertex;
import org.engineeringnotebook.graphmanagerservice.graph.Edge;

// TODO: Auto-generated Javadoc
/**
 * The Interface GraphManager.
 *
 * @author Jeffrey Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org
 */
public interface GraphManager {
  
  /**
   * Creates the graph.
   *
   * @param graphName the graph name
   * @return the string
   */
  public String createGraph(String graphName);
  
  /**
   * Delete graph.
   *
   * @param graphID the graph id
   * @return true, if successful
   */
  public boolean deleteGraph(String graphID);
  
  /**
   * Adds the vertex.
   *
   * @param graphID the graph id
   * @param vertexLabel the vertex label
   * @return the string
   */
  public String addVertex(String graphID, String vertexLabel);
  
  /**
   * Adds the edge.
   *
   * @param graphID the graph id
   * @param edgeLabel the edge label
   * @param vertex1ID the vertex1 id
   * @param vertex2ID the vertex2 id
   * @return the string
   */
  public String addEdge(String graphID, String edgeLabel, String vertex1ID, String vertex2ID);
  
  /**
   * Gets the vertex.
   *
   * @param graphID the graph id
   * @param id the id
   * @return the vertex
   */
  public Vertex getVertex(String graphID, String id);
  
  /**
   * Gets the edge.
   *
   * @param graphID the graph id
   * @param id the id
   * @return the edge
   */
  public Edge getEdge(String graphID, String id);
  
}
