/*
Copyright (c) 2012, University of Texas at El Paso
All rights reserved.
Redistribution and use in source and binary forms, with or without modification, are permitted
provided that the following conditions are met:

	-Redistributions of source code must retain the above copyright notice, this list of conditions
	 and the following disclaimer.
	-Redistributions in binary form must reproduce the above copyright notice, this list of conditions
	 and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/


package edu.utep.trustlab.visko.web.requestHandler.queryExecutionService;

import javax.servlet.http.HttpServletRequest;

import edu.utep.trustlab.visko.web.json.PipelineSetResults;
import edu.utep.trustlab.visko.web.requestHandler.RequestHandlerJSON;
import edu.utep.trustlab.visko.planning.Query;
import edu.utep.trustlab.visko.planning.QueryEngine;
import edu.utep.trustlab.visko.planning.pipelines.PipelineSet;

public class ExecuteQueryServiceServlet  extends RequestHandlerJSON {
	private Query query;

	public String doGet(HttpServletRequest request){
		// TODO Auto-generated method stub

		String stringQuery = request.getParameter("query");
		String stringMaxResults = request.getParameter("maxResults");
		String viewURI = request.getParameter("requiredView");
		
		int maxResults = 5;

		if (stringMaxResults != null)
			maxResults = Integer.parseInt(stringMaxResults);

		String jsonResults;
		PipelineSetResults results = new PipelineSetResults();
		
		if (stringQuery != null) {
			query = new Query(stringQuery);
		
			if (query.isExecutableQuery()) {
				QueryEngine engine = new QueryEngine(query);
				PipelineSet pipelines = engine.getPipelines();
				results.setPipelineSet(pipelines);
				results.setMaxResults(maxResults);
				results.setViewCriteria(viewURI);
				jsonResults = results.toString();
			}
			else
				jsonResults = results.getEmptyResults();
		}
		else
			jsonResults = results.getEmptyResults();
		
		return jsonResults;
	}
}
