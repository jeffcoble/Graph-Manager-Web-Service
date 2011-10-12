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

//SOAP-WSDL stuff
import javax.jws.WebService;
import javax.jws.WebMethod;
import org.engineeringnotebook.graphmanagerservice.graph.Vertex;
import org.engineeringnotebook.graphmanagerservice.graph.Edge;

// TODO: Auto-generated Javadoc
/**
 * The Interface GraphManagerWSEndpoint.
 */
@WebService
public interface GraphManagerWSEndpoint {
	
	/**
	 * Creates the graph.
	 *
	 * @param graphName the graph name
	 * @return the graph ID
	 */
	@WebMethod
	public String createGraph(String graphName);
	
	/**
	 * Delete graph.
	 *
	 * @param graphID the unique ID of the graph
	 * @return true, if successful
	 */
	@WebMethod
  public boolean deleteGraph(String graphID);
	
	/**
	 * Adds a vertex.
	 *
	 * @param graphID the unique ID of the graph
	 * @param vertexName the human-readable vertex name
	 * @return the unique ID of the new vertex
	 */
	@WebMethod
	public String addVertex(String graphID, String vertexName);
	
	/**
	 * Gets a vertex.
	 *
	 * @param graphID the unique ID of the graph
	 * @param vertexID the unique ID of the vertex
	 * @return the vertex
	 */
	@WebMethod
	public Vertex getVertex(String graphID, String vertexID);
	
	/**
	 * Adds an edge.
	 *
	 * @param graphID the unique ID of the graph
	 * @param edgeName the human-readable edge name
	 * @param vertex1ID the unique ID of the vertex
	 * @param vertex2ID the unique ID of the vertex
	 * @return the the unique ID of the new edge
	 */
	@WebMethod
	public String addEdge(String graphID, String edgeName, String vertex1ID, String vertex2ID);
	
	/**
	 * Gets an edge.
	 *
	 * @param graphID the unique ID of the graph
	 * @param edgeID the unique ID of the edge
	 * @return the edge
	 */
	@WebMethod
	public Edge getEdge(String graphID, String edgeID);
	
	/**
   * Graph test.
   *
   * @param inputString the input string
   * @return the echo the inputString
   */
  @WebMethod
  public String graphTest(String inputString);
  

}
