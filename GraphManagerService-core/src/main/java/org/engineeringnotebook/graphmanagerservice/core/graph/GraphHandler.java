/*
 * 
 */
package org.engineeringnotebook.graphmanagerservice.core.graph;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import org.engineeringnotebook.graphmanagerservice.graph.Vertex;
import org.engineeringnotebook.graphmanagerservice.graph.Edge;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphHandler.
 *
 * @author Jeffrey Coble
 * 
 * This class is a wrapper around the JUNG graph so we can maintain some additional metadata
 */

@XmlRootElement(name = "graph")
public class GraphHandler {
  
  /** The Constant logger. */
  private static final Logger logger = Logger.getLogger(Vertex.class.getName());
  
  /** The label. */
  private String label;
  
  /** The id. */
  private UUID id;
  
  /** The graph. */
  private Graph<Vertex, Edge> graph;
  
  /**
   * Instantiates a new graph handler.
   */
  public GraphHandler() {
    id = UUID.randomUUID();
    graph = new SparseMultigraph<Vertex, Edge>(); 
  }
  
  /**
   * Sets the label.
   *
   * @param label the new label
   */
  public void setLabel(String label) {
    this.label = label;
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
  public UUID getID() {
    return id;
  }
  
  /**
   * Gets the graph.
   *
   * @return the graph
   */
  public Graph<Vertex, Edge> getGraph() {
    return graph;
  }
  
  
  /**
   * Adds the vertex.
   *
   * @param vertex the vertex
   * @return the string
   */
  public String addVertex(Vertex vertex) {

    graph.addVertex(vertex);
    return vertex.getID().toString();
    
  }
  
  /**
   * Adds the edge.
   *
   * @param edge the edge
   * @param vertex1ID the vertex1 id
   * @param vertex2ID the vertex2 id
   * @return the string
   */
  public String addEdge(Edge edge, String vertex1ID, String vertex2ID) {
        
    Vertex v1 = getVertex(vertex1ID);
    Vertex v2 = getVertex(vertex2ID);
      
    if((v1 != null) && (v2 != null))
      graph.addEdge(edge, v1, v2);
    
    return edge.getID().toString();
  }
  
  /**
   * Gets the vertex.
   *
   * @param vertexID the vertex id
   * @return the vertex
   */
  public Vertex getVertex(String vertexID) {
    ArrayList vertices;
    Vertex vertex = null;
    
    vertices = new ArrayList(graph.getVertices());
    
    for(int i=0; i<vertices.size(); i++) {
        Vertex v = (Vertex)vertices.get(i);
        if(v.getID().equals(vertexID)) {
            vertex = v;
            break;  
        }
    }
    return vertex;
  }
  
  /**
   * Gets the edge.
   *
   * @param edgeID the edge id
   * @return the edge
   */
  public Edge getEdge(String edgeID) {
    
    ArrayList edges;
    Edge edge = null;
  
    edges = new ArrayList(graph.getEdges());
  
    for(int i=0; i<edges.size(); i++) {
        Edge e = (Edge)edges.get(i);
        if(e.getID().equals(edgeID)) {
            edge = e;
            break;  
        }
    }
    return edge;
  }
  
/**  
  public String getXML(JAXBContext context) {
    
    String xmlString = null;  
    logger.log(Level.FINEST, "GraphHandler: getXML()");
    
    try {
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        m.marshal(this, sw);
        xmlString = sw.toString();
    }
    catch (JAXBException jbe) {
      logger.log(Level.INFO, "Problem marshalling the graph: {0}" +  jbe);  
    }
    return xmlString;
  }
  **/
}
