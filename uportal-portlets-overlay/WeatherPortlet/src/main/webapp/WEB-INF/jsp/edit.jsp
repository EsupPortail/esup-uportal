<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>

<%-- Author: Dustin Schultz | Version $Id$ --%>
<%@ include file="/WEB-INF/jsp/include.jsp" %>

<spring:message var="delImgSrc" code="del.img.full.path"/>
<spring:message var="delImgWidth" code="del.img.width"/>
<spring:message var="delImgHeight" code="del.img.height"/>

<portlet:actionURL var="saveOrderUrl">
    <portlet:param name="action" value="saveOrder"/>
</portlet:actionURL>
<portlet:actionURL var="updateUnitsUrl">
    <portlet:param name="action" value="updateUnits"/>
</portlet:actionURL>
<portlet:actionURL var="deleteLocationUrl">
    <portlet:param name="action" value="delete"/>
</portlet:actionURL>


<div id="${n}jasigWeatherPortlet" class="jasigWeatherPortlet">
    <script type="text/javascript" src="<rs:resourceURL value="/rs/jquery/1.3.2/jquery-1.3.2.min.js"/>"></script>
    <script type="text/javascript" src="<rs:resourceURL value="/rs/jqueryui/1.7.2/jquery-ui-1.7.2.min.js"/>"></script>
    <script type="text/javascript" src="<rs:resourceURL value="/rs/fluid/1.1.2/js/fluid-all-1.1.2.min.js"/>"></script>
    <script type="text/javascript" src="<rs:resourceURL value="/rs/jquery/form/2.28/jquery.form-2.28.min.js"/>"></script>
    <script type="text/javascript" src="${context}/js/weather.min.js"></script>
    <script type="text/javascript">
    weatherPortlet.jQuery(document).ready(function() {
        $ = weatherPortlet.jQuery;
        var cityReorderer, cityEditor;
        
        var cityEditorOpts = {
            saveOrderUrl : '${saveOrderUrl}',
            updateUnitUrl : '${updateUnitsUrl}',
            deleteLocationUrl : '${deleteLocationUrl}',
            noLocationsMessage: '<spring:message code="edit.noresults.location"/>',
            searchingMessage: '<spring:message code="edit.search.loading"/>',
            listeners : {
                //Closure to deal with cityReorderer being initialized after these options are created
                addCity : function() {
                    cityReorderer.refresh();
                },
                deleteCity : function() {
                    cityReorderer.refresh();
                }
            }
        };
        
        cityEditor = weatherPortlet.editCities("#${n}jasigWeatherPortlet", cityEditorOpts);
        
        var reordererOpts = {
            selectors: {
                movables: ".movable",
                grabHandle: ".handle"
            },
            listeners : {
                onRefresh : cityEditor.refreshLocationRows,
                afterMove : cityEditor.saveOrder
            }
        };
        
        cityReorderer = weatherPortlet.fluid.reorderList("#${n}savedLocationsTable", reordererOpts);
    });
    </script>
        
    <div class="location-search" style="display:none">
        <h2><spring:message code="edit.add.location.button"/></h2>
        <form id="${n}locationSearchForm" class="locate-search-form" action="${context}/ajax/findCity">
	        <p><label class="portlet-form-label"><spring:message code="edit.enter.location"/>:</label></p>
	        <input name="location" class="portlet-form-input-field"/>
	        <spring:message var="search" code="edit.search.button"/>
	        <input type="submit" name="submit" value="${search}" class="portlet-form-button"/>
            <button class="portlet-form-button weather-search-form-cancel"><spring:message code="edit.cancel.search.button"/></button>
        </form>
        <p class="search-message" style="display:none"><spring:message code="edit.search.loading"/></p>
    </div>
    <div class="search-results" style="display:none">
        <h2><spring:message code="edit.add.location.button"/></h2>
        <portlet:actionURL var="selectLocationUrl">
            <portlet:param name="action" value="add"/>
        </portlet:actionURL>
        <form id="${n}addLocationForm" class="select-location-form" action="${selectLocationUrl}" method="post">
            <p><label class="portlet-form-label"><spring:message code="edit.multiple.locations"/>:</label></p>
            <select name="locationCode" class="portlet-form-input-field"></select>
            
            <p><label class="portlet-form-label"><spring:message code="edit.select.metric"/>:</label></p>
            <select name="unit" class="portlet-form-input-field">
                <option value="F"><spring:message code="edit.standard.option"/></option>
                <option value="C"><spring:message code="edit.metric.option"/></option>
            </select>
            <br/><br/>
            <spring:message var="addLocation" code="edit.add.location.button"/>
            <spring:message var="cancelSearch" code="edit.cancel.search.button"/>
            <input type="submit" value="${addLocation}" class="portlet-form-button"/>
            <button class="portlet-form-button weather-search-form-cancel"><spring:message code="edit.cancel.search.button"/></button>
        </form>
    </div>
    <div class="locations">
    
        <h2><spring:message code="edit.locations.title"/></h2>
    
        <p><spring:message code="edit.saved.locations.title"/></p>
        <form id="${n}editLocationForm">
        <table id="${n}savedLocationsTable">
            <thead>
	            <tr>
                    <th><spring:message code="edit.saved.locations.column.order"/></th>
                    <th><spring:message code="edit.saved.locations.column.location"/></th>
                    <th><spring:message code="edit.saved.locations.column.measurement"/></th>
                    <th><spring:message code="edit.saved.locations.column.delete"/></th>
	            </tr>
            </thead>
            <tbody>
                <tr class="template" style="display: none">
                    <input type="hidden" name="code" value=""/>
                    <td class="handle">X.</td>
                    <td class="location-name"></td>
                    <td>
                        <select name="unit_" id="${n}temperatureUnit_" class="portlet-form-input-field select-units">
                            <option value="F"><spring:message code="edit.standard.option"/></option>
                            <option value="C"><spring:message code="edit.metric.option"/></option>
                        </select>
                    </td>
                    <td class="delete"><a href="javascript:;" class="delete-location-link"><img width="${delImgWidth}" height="${delImgHeight}" src="${context}/${delImgSrc}"/></a></td>
                </tr>
                <spring:message var="delImgSrc" code="del.img.full.path"/>
                <spring:message var="delImgWidth" code="del.img.width"/>
                <spring:message var="delImgHeight" code="del.img.height"/>
                <c:forEach var="savedLocation" items="${savedLocations}" varStatus="status">
                    <tr class="movable">
                        <input type="hidden" name="code" value="${savedLocation.code}"/>
                        <td class="handle">${status.count}.</td>
                        <td class="location-name"><spring:message htmlEscape="true" text="${savedLocation.name}" /></td>
                        <td>
                            <select name="unit_${savedLocation.code}" id="${n}temperatureUnit_${savedLocation.code}" class="portlet-form-input-field select-units">
                                <option value="F" ${savedLocation.temperatureUnit == 'F' ? 'selected="true"' : ''}><spring:message code="edit.standard.option"/></option>
                                <option value="C" ${savedLocation.temperatureUnit == 'C' ? 'selected="true"' : ''}><spring:message code="edit.metric.option"/></option>
                            </select>
                        </td>
                        <td class="delete"><a href="javascript:;" class="delete-location-link"><img width="${delImgWidth}" height="${delImgHeight}" src="${context}/${delImgSrc}"/></a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </form>
        <p><a class="add-location-link" href="javascript:;"><spring:message code="edit.add.location.button"/></a></p>
	    
	    <portlet:renderURL var="formDoneAction" portletMode="VIEW" windowState="NORMAL"/>
	    <p><button onclick="window.location='${formDoneAction}'" class="portlet-form-button"><spring:message code="edit.done.button"/></button></p>
    </div>
</div>
