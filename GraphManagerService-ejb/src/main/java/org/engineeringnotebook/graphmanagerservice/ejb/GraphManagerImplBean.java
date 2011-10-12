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
package org.engineeringnotebook.graphmanagerservice.ejb;

import org.engineeringnotebook.graphmanagerservice.core.GraphManagerImplBeanLocal;
import org.engineeringnotebook.graphmanagerservice.core.GraphManagerImplBeanRemote;
import javax.ejb.Stateless;
import java.util.UUID;
import org.engineeringnotebook.graphmanagerservice.graph.Vertex;
import org.engineeringnotebook.graphmanagerservice.graph.VertexFactory;
import org.engineeringnotebook.graphmanagerservice.graph.Edge;
import org.engineeringnotebook.graphmanagerservice.graph.EdgeFactory;
import org.engineeringnotebook.graphmanagerservice.core.graph.GraphHandler;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphManagerImplBean.
 *
 * @author Jeffrey Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org
 */

@Stateless
public class GraphManagerImplBean implements GraphManagerImplBeanLocal, GraphManagerImplBeanRemote {

  /** The graph list. */
  List<GraphHandler> graphList = new CopyOnWriteArrayList<GraphHandler>();
  
  /** The vertex factory. */
  VertexFactory vertexFactory = new VertexFactory();
  
  /** The edge factory. */
  EdgeFactory edgeFactory = new EdgeFactory();
  
  /** The Constant logger. */
  private static final Logger logger = Logger.getLogger(GraphManagerImplBean.class.getName());
  
  
  /**
   * Instantiates a new graph manager impl bean.
   */
  public GraphManagerImplBean() {
        logger.log(Level.INFO, "Creating the GraphManagerBean");  
    }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.core.GraphManager#createGraph(java.lang.String)
   */
  @Override
  public String createGraph(String graphName) {
    GraphHandler graphHandler = new GraphHandler(); 
    graphHandler.setLabel(graphName);
    graphList.add(graphHandler);
    return graphHandler.getID().toString();
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.core.GraphManager#deleteGraph(java.lang.String)
   */
  @Override
  public boolean deleteGraph(String id) {
    UUID graphID = UUID.fromString(id);
    int graphIndex = -1;
    boolean graphDeleted;
    
    //look for the graph matching the ID
    for(int i=0; i<graphList.size(); i++) {
      GraphHandler gh = (GraphHandler)graphList.get(i);
      if(gh.getID().equals(graphID)) {
        graphIndex = i;
        break;  
      }
    }
    //if we found the graph, remove it from the list
    if(graphIndex >= 0) {
      graphList.remove(graphIndex);
      graphDeleted = true;
    }
    else
      graphDeleted = false;
    
    return graphDeleted;
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.core.GraphManager#addVertex(java.lang.String, java.lang.String)
   */
  @Override
  public String addVertex(String graphID, String vertexLabel) {
    String vertexID = null;
    
    System.out.println("addVertex -- graphID = " + graphID);
    
    GraphHandler graphHandler = getGraph(graphID);
    if(graphHandler != null) {
      System.out.println("*** Creating the Vertex ***");
      Vertex v = vertexFactory.createVertex(vertexLabel, UUID.randomUUID().toString());
      vertexID = graphHandler.addVertex(v);
    }
    else
      logger.log(Level.INFO, "NO GRAPH FOUND!!!");  
    
    return vertexID;
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.core.GraphManager#addEdge(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public String addEdge(String graphID, String edgeLabel, String vertex1ID, String vertex2ID) {
    String edgeID = null;
    
    GraphHandler graphHandler = getGraph(graphID);
    if(graphHandler != null) {
      Edge e = edgeFactory.createEdge(edgeLabel, UUID.randomUUID().toString());
      edgeID = graphHandler.addEdge(e, vertex1ID, vertex2ID);
    }
    else
      logger.log(Level.INFO, "NO GRAPH FOUND!!!");  
    
    return edgeID;
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.core.GraphManager#getVertex(java.lang.String, java.lang.String)
   */
  @Override
  public Vertex getVertex(String graphID, String id) {
    
    System.out.println("GraphManagerImplBean -- Get Vertex id " + id + " for Graph " + graphID);
    GraphHandler graphHandler = getGraph(graphID);
    Vertex v = graphHandler.getVertex(id);
    
    if(v == null)
      System.out.println("Stupid Vertex is NULL!!!");
    
    return v;
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.core.GraphManager#getEdge(java.lang.String, java.lang.String)
   */
  @Override
  public Edge getEdge(String graphID, String id) {
   
    GraphHandler graphHandler = getGraph(graphID);
    Edge e = graphHandler.getEdge(id);
    
    return e;
  }
  
  /**
   * Gets the graph.
   *
   * @param id the id
   * @return the graph
   */
  private GraphHandler getGraph(String id) {
    System.out.println("Getting Graph ID: " + id);
    UUID graphID = UUID.fromString(id);
    GraphHandler graphHandler = null;
    
    for(int i=0; i<graphList.size(); i++) {
      GraphHandler gh = (GraphHandler)graphList.get(i);
      if(gh.getID().equals(graphID)) {
        graphHandler = gh;
          break;  
      }
    }
    
    return graphHandler;
  }
}
