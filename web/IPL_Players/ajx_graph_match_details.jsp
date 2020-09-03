<%-- 
    Document   : ajx_graph_match_details
    Created on : 27 Aug, 2020, 4:00:39 AM
    Author     : Abhinav Kumar
--%>
<%@page import="java.util.Iterator"%>
<%@page import="IPL_BEANS.MatchBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="IPL_LOGIC.MatchLogic"%>
<%@page import="IPL_BEANS.UserBean"%>
<%@page import="IPL_LOGIC.MatchGraphLogic"%>
<%@page import="IPL_BEANS.MatchGraphBean"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<%
    String matchId = "";
    Gson gsonObj = new Gson();
    Map<Object, Object> map = null;
    Map<Object, Object> map1 = null;
    Map<Object, Object> map2 = null;
    List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
    List<Map<Object, Object>> list1 = new ArrayList<Map<Object, Object>>();
    List<Map<Object, Object>> list2 = new ArrayList<Map<Object, Object>>();
    ArrayList<MatchGraphBean> graphDataList = (ArrayList<MatchGraphBean>) session.getAttribute("graphDataList");
    Iterator<MatchGraphBean> graphListIterator = graphDataList.iterator();
    while (graphListIterator.hasNext()) {
        MatchGraphBean matchGraphBean = graphListIterator.next();
        map = new HashMap<Object, Object>();
        map1 = new HashMap<Object, Object>();
        map2 = new HashMap<Object, Object>();
        matchId=matchGraphBean.getMatchId();
        map.put("label", matchGraphBean.getTeamId());
        map.put("y", Double.parseDouble(matchGraphBean.getTeamAmount()));
        list.add(map);
        map1.put("label", matchGraphBean.getTeamId());
        int avgPoint = (Integer.parseInt(matchGraphBean.getTeamAmount())/Integer.parseInt(matchGraphBean.getTeamCount()));
        map1.put("y", Double.valueOf(avgPoint));
        list1.add(map1);
        map2.put("label", matchGraphBean.getTeamId());
        map2.put("y", Double.parseDouble(matchGraphBean.getTeamCount()));
        list2.add(map2);
    }
    String dataPoints = gsonObj.toJson(list);
    String dataPoints1 = gsonObj.toJson(list1);
    String dataPoints2 = gsonObj.toJson(list2);
%>
<script type="text/javascript">
    window.onload = function() { 
 
        var chart = new CanvasJS.Chart("chartContainer", {
            title: {
                text: "Total Team point after Match " + <%=matchId%>
            },
            axisX: {
                title: "Teams"
            },
            axisY: {
                title: "Points",
                includeZero: true
            },
            data: [{
                    type: "column",
                    yValueFormatString: "#,##0#"        ,
                    dataPoints: <%out.print(dataPoints);%>
                }]
        });
        var chart1 = new CanvasJS.Chart("chartContainer1", {
            title: {
                text: "Average Team point after Match " + <%=matchId%>
            },
            axisX: {
                title: "Teams"
            },
            axisY: {
                title: "Points",
                includeZero: true
            },
            data: [{
                    type: "column",
                    yValueFormatString: "#,##0#"        ,
                    dataPoints: <%out.print(dataPoints1);%>
                }]
        });
        
        var chart2 = new CanvasJS.Chart("chartContainer2", {
            title: {
                text: "Total Active player after Match " + <%=matchId%>
            },
            axisX: {
                title: "Teams"
            },
            axisY: {
                title: "Points",
                includeZero: true
            },
            data: [{
                    type: "column",
                    yValueFormatString: "#,##0#"        ,
                    dataPoints: <%out.print(dataPoints2);%>
                }]
        });
        chart.render();
        chart1.render();
        chart2.render();
    }
</script>
<div id="chartContainer" style="height: 370px; width: 70%; border-style: solid;"></div>
<BR/><BR/>
<div id="chartContainer1" style="height: 370px; width: 70%; border-style: solid;"></div>
<BR/><BR/>
<div id="chartContainer2" style="height: 370px; width: 70%; border-style: solid;"></div>