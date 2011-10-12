/*
 * 
 */
package org.engineeringnotebook.graphmanagerservice.graph;

import org.engineeringnotebook.graphmanagerservice.graph.Edge;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Edge objects.
 */
public class EdgeFactory {
  
  /**
   * Creates a new Edge object.
   *
   * @param edgeLabel the edge label
   * @param edgeID the edge id
   * @return the edge
   */
  public Edge createEdge(String edgeLabel, String edgeID) {
    Edge e = new Edge();
    e.setLabel(edgeLabel);
    e.setID(edgeID);
    
    return e;
  }

}
