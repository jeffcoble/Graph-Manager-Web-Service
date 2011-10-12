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
package org.engineeringnotebook.graphmanagerservice.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.engineeringnotebook.graphmanagerservice.core.GraphManagerImplBeanRemote;
import org.engineeringnotebook.graphmanagerservice.graph.Vertex;
import org.engineeringnotebook.graphmanagerservice.graph.Edge;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphManagerUtilImpl.
 *
 * @author Jeffrey Coble
 * 
 * Used by the endpoint implementations (RESTful and WS) to access the underlying Graph Manager implementation
 */

public class GraphManagerUtilImpl implements GraphManagerUtil {
  
  /** The graph manager ejb. */
  private GraphManagerImplBeanRemote graphManagerEJB; 
  
  /**
   * Instantiates a new graph manager util impl.
   */
  public GraphManagerUtilImpl() {
    //get the EJB reference
    connectEJB();
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.util.GraphManagerUtil#createGraph(java.lang.String)
   */
  @Override
  public String createGraph(String graphName) {
    
    String graphID = graphManagerEJB.createGraph(graphName);
    return graphID;
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.util.GraphManagerUtil#deleteGraph(java.lang.String)
   */
  @Override
  public boolean deleteGraph(String graphID) {
    return graphManagerEJB.deleteGraph(graphID);
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.util.GraphManagerUtil#addVertex(java.lang.String, java.lang.String)
   */
  @Override
  public String addVertex(String graphID, String vertexName) {
    String vertexID = null;
    if(vertexName != null){
        //connectEJB();
        vertexID = graphManagerEJB.addVertex(graphID, vertexName);
    }  
    
    return vertexID; 
} 
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.util.GraphManagerUtil#addEdge(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public String addEdge(String graphID, String edgeName, String vertex1ID, String vertex2ID) {
    String edgeID = null;
    
    if((vertex1ID != null) && (vertex2ID !=null)){
      //connectEJB();
      edgeID = graphManagerEJB.addEdge(graphID, edgeName, vertex1ID, vertex2ID);
    }
    
    return edgeID; 
  }  
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.util.GraphManagerUtil#getVertex(java.lang.String, java.lang.String)
   */
  @Override
  public Vertex getVertex(String graphID, String vertexID) {
    
    Vertex v = null;
    
    if(vertexID != null){
      //connectEJB();
      v = graphManagerEJB.getVertex(graphID, vertexID);
    }      
    return v;  
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.util.GraphManagerUtil#getEdge(java.lang.String, java.lang.String)
   */
  @Override
  public Edge getEdge(String graphID, String edgeID) {

    Edge e = null;
    
    if(edgeID != null){
      //connectEJB();
      e = graphManagerEJB.getEdge(graphID, edgeID);
    }      
    return e;      
  }
  
  /**
   * Called to get the reference to the EJB.
   */
  private void connectEJB() {

      Context context;
      try
      {
          context = new InitialContext();
          graphManagerEJB = (GraphManagerImplBeanRemote)context.lookup("org.engineeringnotebook.graphmanagerservice.core.GraphManagerImplBeanRemote");
      } catch (NamingException e)
      {
          e.printStackTrace();
          throw new RuntimeException(e);
      }
  }  

}
