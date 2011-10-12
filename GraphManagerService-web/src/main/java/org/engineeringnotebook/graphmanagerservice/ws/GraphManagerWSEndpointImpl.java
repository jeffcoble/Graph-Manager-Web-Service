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
package org.engineeringnotebook.graphmanagerservice.ws;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.engineeringnotebook.graphmanagerservice.graph.Vertex;
import org.engineeringnotebook.graphmanagerservice.graph.Edge;
import org.engineeringnotebook.graphmanagerservice.util.GraphManagerUtilImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphManagerWSEndpointImpl.
 *
 * @author Jeffrey Coble
 * 
 * Provides the JAX-WS endpoint implementation for the Graph Manager Service.  This is one of two
 * endpoints, the other being RESTful (JAX-RS).  The Graph Manager Service implementation is provided by
 * a Stateless EJB, which is accessed through the GraphManagerUtil class.
 * 
 * Once deployed, the WSDL will be available at http://base-path:port/GraphManagerService-web/GraphManagerImplService?wsdl
 */

@WebService(endpointInterface = "org.engineeringnotebook.graphmanagerservice.ws.GraphManagerWSEndpoint")
public class GraphManagerWSEndpointImpl implements GraphManagerWSEndpoint{
  
  /** The Constant logger. */
  private static final Logger logger = Logger.getLogger(GraphManagerWSEndpointImpl.class.getName());
  
  /** The Constant context. */
  static final JAXBContext context = initContext();
  
  /** The graph util. */
  private GraphManagerUtilImpl graphUtil = new GraphManagerUtilImpl(); 
  
	/* (non-Javadoc)
	 * @see org.engineeringnotebook.graphmanagerservice.ws.GraphManagerWSEndpoint#graphTest(java.lang.String)
	 */
	@Override
	public String graphTest(String inputString) {
		return "Got " + inputString;
	}
	
	/* (non-Javadoc)
	 * @see org.engineeringnotebook.graphmanagerservice.ws.GraphManagerWSEndpoint#createGraph(java.lang.String)
	 */
	@Override
	public String createGraph(String graphName) {
	  String graphID = graphUtil.createGraph(graphName);
	  
	  logger.log(Level.SEVERE, "GraphManagerImpl: createGraph: {0}" + graphID);   
	  
	  return graphID;
	}
	
	/* (non-Javadoc)
	 * @see org.engineeringnotebook.graphmanagerservice.ws.GraphManagerWSEndpoint#deleteGraph(java.lang.String)
	 */
	@Override
	public boolean deleteGraph(String graphID) {
	  boolean deletedGraph = graphUtil.deleteGraph(graphID);
	  
	  return deletedGraph;
	}
	
	/* (non-Javadoc)
	 * @see org.engineeringnotebook.graphmanagerservice.ws.GraphManagerWSEndpoint#addVertex(java.lang.String, java.lang.String)
	 */
	@Override
	public String addVertex(String graphID, String vertexName) {
		String vertexID = graphUtil.addVertex(graphID, vertexName);
    
    logger.log(Level.SEVERE, "GraphManagerImpl: addVertex: {0}" + vertexID);		
    
    return vertexID;
	}
	
	/* (non-Javadoc)
	 * @see org.engineeringnotebook.graphmanagerservice.ws.GraphManagerWSEndpoint#getVertex(java.lang.String, java.lang.String)
	 */
	@Override
	public Vertex getVertex(String graphID, String vertexID) {
	  Vertex v = graphUtil.getVertex(graphID, vertexID);
	  
	  logger.log(Level.SEVERE, "GraphManagerImpl: getVertex: {0}" + vertexID);
	      
	  return v;  
	}
	
	/* (non-Javadoc)
	 * @see org.engineeringnotebook.graphmanagerservice.ws.GraphManagerWSEndpoint#addEdge(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String addEdge(String graphID, String edgeName, String vertex1ID, String vertex2ID) {
	  String edgeID = graphUtil.addEdge(graphID, edgeName, vertex1ID, vertex2ID);
	  
	  logger.log(Level.SEVERE, "GraphManagerImpl: addEdge: {0} barfed for some reason" + edgeName);

	  return edgeID;
	}
	
	/* (non-Javadoc)
	 * @see org.engineeringnotebook.graphmanagerservice.ws.GraphManagerWSEndpoint#getEdge(java.lang.String, java.lang.String)
	 */
	@Override
	public Edge getEdge(String graphID, String edgeID) {
	  Edge e = graphUtil.getEdge(graphID, edgeID);
	  
	  logger.log(Level.SEVERE, "GraphManagerImpl: getEdge: {0}" + edgeID);
	      
	  return e; 
	}
	
  /**
   * Inits the context.
   *
   * @return the jAXB context
   */
  private static JAXBContext initContext() {
    JAXBContext jContext = null;
    
    try {  
        jContext = JAXBContext.newInstance(Vertex.class, Edge.class);
    }
    catch (JAXBException jbe) {
      logger.log(Level.INFO, "Problem Creating JAXB Context: {0}", jbe);  
    }
    
    return jContext;
  }
	
}
