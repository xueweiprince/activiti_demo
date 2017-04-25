/**
 * 工具类
 */

/**
 * 获取上下文路径，主机路径，当前路径
 */
function getContextPath(){
		debugger;
		//当前网址(http://localhost:8081/springActiviti/activiti/deployList.jsp)
		var currentPath=window.document.location.href;
		//获取主机之后的路径(/springActiviti/activiti/deployList.jsp)
		var pathName=window.document.location.pathname;
		
		var pos=currentPath.indexOf(pathName);
		//获取主机路径(http://localhost:8081)
		var localhostPath=currentPath.substring(0,pos);
		//获取上下文路径
		var contextPath=localhostPath+pathName.substr(0,pathName.substr(1).indexOf('/')+1);
		return {"currentPath":currentPath,"pathName":pathName,"contextPath":contextPath};
		
	}
	
	/**
	 * 获取路径中的参数值
	 * @param key 参数名
	 * @returns
	 */
	function getRequestParam(key){
		debugger;
		var currentPath=window.document.location.href;
		var paramStr="";
		var paramArray=new Array();
		if((position=currentPath.indexOf("?"))!=-1){
			paramStr=currentPath.substr(position+1);
			paramArray=paramStr.split("&");
		}
		var params={};
		if(paramArray!=null&&paramArray.length!=0){
			$(paramArray).each(function(i,v){
				debugger;
				kv=v.split("=");
				params[kv[0]]=kv[1];
			});
		}
		return params[key];
	}