package org.openvisko.module;

import org.openvisko.module.operators.ToolkitOperator;
import org.openvisko.module.util.FileUtils;
import org.openvisko.module.util.GetURLContents;

public class JSONGraph_OperatorPaths extends ToolkitOperator
{
	public JSONGraph_OperatorPaths(){	
		super(null, null, true, true, "operatorPaths.json");
	}

	public String transform(){
		String webServerURL = FileUtils.getServerBaseURL().toString();
		String json = GetURLContents.downloadText(webServerURL + "/visko-web/ViskoServletManager?requestType=knowledge-base-info&info=pipelines");
		FileUtils.writeTextFile(json, FileUtils.getOutputDir().getAbsolutePath(), outputFileName);
		return outputURL;
	}
}
