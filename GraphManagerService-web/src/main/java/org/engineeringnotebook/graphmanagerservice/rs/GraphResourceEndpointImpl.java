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
package org.engineeringnotebook.graphmanagerservice.rs;

//REST stuff
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.UriBuilder;
//Everything else
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
import java.net.URI;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.engineeringnotebook.graphmanagerservice.graph.Vertex;
import org.engineeringnotebook.graphmanagerservice.graph.Edge;
import org.engineeringnotebook.graphmanagerservice.util.GraphManagerUtilImpl;

// TODO: Auto-generated Javadoc
/**
 * The Class GraphResourceEndpointImpl.
 *
 * @author Jeffrey Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org
 * 
 * ToDo:  The responses need to be configured to send back failure codes
 */

//The root path annotation belongs in the class, not in the interface, because a resource
//is a unique thing that will be specific to the interface's implementation.  At least that's
//my reverse-engineered opinion after the Jersey JAX-RS implementation fails to load with
//the root path annotation on the interface.
@Path("/graph")
public class GraphResourceEndpointImpl implements GraphResourceEndpoint{
  
  /** The uri info. */
  @javax.ws.rs.core.Context
  private UriInfo uriInfo;
  
  /** The Constant logger. */
  private static final Logger logger = Logger.getLogger(GraphResourceEndpointImpl.class.getName());
  //static final JAXBContext context = initContext();
  /** The graph util. */
  private GraphManagerUtilImpl graphUtil = new GraphManagerUtilImpl();
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.rs.GraphResourceEndpoint#createGraph(java.lang.String)
   */
  @Override
  public Response createGraph(String graphName) {
    String graphID = graphUtil.createGraph(graphName);
    
    UriBuilder ub = uriInfo.getAbsolutePathBuilder();
    URI graphUri = ub.path(graphID).build();
    
    return Response.ok(graphUri.toASCIIString()).build(); 
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.rs.GraphResourceEndpoint#deleteGraph(java.lang.String)
   */
  @Override
  public Response deleteGraph(String graphID) {
    Response response;
    
    boolean graphDeleted = graphUtil.deleteGraph(graphID);
    
    //if the graph was deleted, respond with OK.  If not, respond with "NOT FOUND".
    if(graphDeleted)
      response = Response.ok().build();
    else
      response = Response.status(Response.Status.NOT_FOUND).build();
    
    return response;
  }
  
  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.rs.GraphResourceEndpoint#addVertex(java.lang.String, java.lang.String)
   */
  @Override
  public Response addVertex(String graphID, String vertexName) {
    
    String vertexID = graphUtil.addVertex(graphID, vertexName);
    
    logger.log(Level.SEVERE, "GraphManager resource: addVertex: {0}", vertexID);
    
    UriBuilder ub = uriInfo.getAbsolutePathBuilder();
    URI vertexUri = ub.path(vertexID).build();
    
    return Response.ok(vertexUri.toASCIIString()).build(); 
} 

  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.rs.GraphResourceEndpoint#addEdge(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
   */
  @Override 
  public Response addEdge(String graphID, String edgeName, String vertex1ID, String vertex2ID) {
    
    String edgeID = graphUtil.addEdge(graphID, edgeName, vertex1ID, vertex2ID);
   
    logger.log(Level.SEVERE, "GraphManager resource: addEdge: {0}", edgeName);
   
    //need to figure out how to set the response if input parms are invalid
    UriBuilder ub = uriInfo.getAbsolutePathBuilder();
    URI edgeUri = ub.path(edgeID).build();
    
    return Response.ok(edgeUri.toASCIIString()).build(); 
  }

  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.rs.GraphResourceEndpoint#getVertexXML(java.lang.String, java.lang.String)
   */
  @Override
  public Vertex getVertexXML(String graphID, String vertexID) {
    
    Vertex v = getVertex(graphID, vertexID);
    
    return v; 
  }

  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.rs.GraphResourceEndpoint#getVertexJSON(java.lang.String, java.lang.String)
   */
  @Override
  public Vertex getVertexJSON(String graphID, String vertexID) {
    
    Vertex v = getVertex(graphID, vertexID);
    
    return v;  
  }

  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.rs.GraphResourceEndpoint#getEdgeXML(java.lang.String, java.lang.String)
   */
  @Override
  public Edge getEdgeXML(@QueryParam("graphid") String graphID, @PathParam("edgeid") String edgeID) {
    Edge e = getEdge(graphID, edgeID);
    
    return e;
  }

  /* (non-Javadoc)
   * @see org.engineeringnotebook.graphmanagerservice.rs.GraphResourceEndpoint#getEdgeJSON(java.lang.String, java.lang.String)
   */
  @Override
  public Edge getEdgeJSON(@QueryParam("graphid") String graphID, @PathParam("edgeid") String edgeID) {
    Edge e = getEdge(graphID, edgeID);
    
    return e;
  }

  /**
   * Gets the vertex.
   *
   * @param graphID the graph id
   * @param vertexID the vertex id
   * @return the vertex
   */
  private Vertex getVertex(String graphID, String vertexID) {
    System.out.println("Calling getVertex method");
    
    Vertex v = graphUtil.getVertex(graphID, vertexID);
    
    System.out.println("*** Got Vertex: " + v.getID() + " ***");
    
    logger.log(Level.SEVERE, "GraphManager resource: getVertex: {0}", vertexID);
     
    return v;  
  }

  /**
   * Gets the edge.
   *
   * @param graphID the graph id
   * @param edgeID the edge id
   * @return the edge
   */
  private Edge getEdge(String graphID, String edgeID) {
  
    Edge e = graphUtil.getEdge(graphID, edgeID);
    
    logger.log(Level.SEVERE, "GraphManager resource: getEdge: {0}", edgeID);
        
    return e;      
  }


  /**
   * Graph test.
   *
   * @param inputString the input string
   * @return the string
   */
  @GET
  @Produces("text/plain")
  @Path("/graphtest")
  public String graphTest(@QueryParam("inputstring") String inputString) {
        logger.log(Level.INFO, "GraphManager resource: graphTest");
      
        System.out.println("Logger = " + GraphResourceEndpointImpl.class.getName());
        UriBuilder ub = uriInfo.getAbsolutePathBuilder();
        URI userUri = ub.path(inputString).build();
      
        
        //URI uri =  uriInfo.getAbsolutePath();
      return("output = " + userUri.toASCIIString());    
  }

  /**
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
  **/
}
