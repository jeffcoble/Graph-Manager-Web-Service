/*
 * 
 */
package org.engineeringnotebook.graphmanagerservice.graph;

import org.engineeringnotebook.graphmanagerservice.graph.Vertex;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Vertex objects.
 *
 * @author Jeffrey Coble
 * 
 * A factory class is really only valuable when the objects it returns represent realizations of an interface.
 * So, Vertex should really be an interface instead of a concrete class.
 */
public class VertexFactory {
  
  /**
   * Creates a new Vertex object.
   *
   * @param vertexLabel the vertex label
   * @param vertexID the vertex id
   * @return the vertex
   */
  public Vertex createVertex(String vertexLabel, String vertexID) {
    Vertex v = new Vertex();
    v.setLabel(vertexLabel);
    v.setID(vertexID);
    return v;
  }

}
