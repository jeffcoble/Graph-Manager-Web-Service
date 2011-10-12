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

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.engineeringnotebook.graphmanagerservice.graph.Vertex;
import org.engineeringnotebook.graphmanagerservice.graph.Edge;

// TODO: Auto-generated Javadoc
/**
 * The Interface GraphResourceEndpoint.
 *
 * @author Jeffrey Coble
 * 
 * The purpose of this interface is to allow different implementing classes to provide a different URI
 * structure for the resources, which can be done by overriding the JAX-RS annotations.
 */
@Produces("text/plain")
public interface GraphResourceEndpoint {
  
  /**
   * Creates the graph.
   *
   * @param graphName the human-readable name of the graph
   * @return the HTTP response code and the URI of the graph
   */
  @POST
  @Consumes("text/plain")
  public Response createGraph(@QueryParam("graphname") String graphName); 
  
  /**
   * Delete graph.
   *
   * @param graphID the UUID of the graph
   * @return the HTTP response code
   */
  @DELETE
  @Consumes("text/plain")
  @Path("{graphid}")
  public Response deleteGraph(@PathParam("graphid") String graphID);
  
  /**
   * Adds the vertex.
   *
   * @param graphID the UUID of the graph
   * @param vertexName the human-readable name of the vertex
   * @return the HTTP response code and the URI of the vertex
   */
  @POST
  @Consumes("text/plain")
  @Path("{graphid}/vertex")
  public Response addVertex(@PathParam("graphid") String graphID, @QueryParam("vertexname") String vertexName);
  
  /**
   * Adds the edge.
   *
   * @param graphID the UUID of the graph
   * @param edgeName the human-readable name of the edge
   * @param vertex1ID the UUID of vertex1
   * @param vertex2ID the UUID of vertex2
   * @return the HTTP response code and the URI of the edge
   */
  @POST
  @Consumes("text/plain")
  @Path("{graphid}/edge")
  public Response addEdge(@PathParam("graphid") String graphID, @QueryParam("edgename") String edgeName, 
      @QueryParam("vertex1") String vertex1ID, @QueryParam("vertex2") String vertex2ID);
  
  /**
   * Gets the vertex xml.
   *
   * @param graphID the UUID of the graph
   * @param vertexID the UUID of the vertex
   * @return the XML representation of the vertex
   */
  @GET
  @Produces("application/xml")
  @Path("{graphid}/vertex/{vertexid}.xml")
  public Vertex getVertexXML(@PathParam("graphid") String graphID, @PathParam("vertexid") String vertexID);
  
  /**
   * Gets the vertex json.
   *
   * @param graphID the UUID of the graph
   * @param vertexID the UUID of the vertex
   * @return the JSON representation of the vertex
   */
  @GET
  @Produces("application/json")
  @Path("{graphid}/vertex/{vertexid}.json")
  public Vertex getVertexJSON(@PathParam("graphid") String graphID, @PathParam("vertexid") String vertexID);
  
  /**
   * Gets the edge xml.
   *
   * @param graphID the UUID of the graph
   * @param edgeID the UUID of the edge
   * @return the XML representation of the edge
   */
  @GET
  @Produces("application/xml")
  @Path("{graphid}/edge/{edgeid}.xml")
  public Edge getEdgeXML(@PathParam("graphid") String graphID, @PathParam("edgeid") String edgeID);
  
  /**
   * Gets the edge json.
   *
   * @param graphID the UUID of the graph
   * @param edgeID the UUID of the edge
   * @return the JSON representation of the edge
   */
  @GET
  @Produces("application/json")
  @Path("{graphid}/edge/{edgeid}.json")
  public Edge getEdgeJSON(@PathParam("graphid") String graphID, @PathParam("edgeid") String edgeID);

}
