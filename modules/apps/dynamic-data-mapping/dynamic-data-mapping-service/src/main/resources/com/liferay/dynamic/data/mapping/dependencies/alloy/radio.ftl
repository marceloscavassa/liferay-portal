<#include "../init.ftl">

<#if required>
	<#assign label = label + " (" + languageUtil.get(requestedLocale, "required") + ")">
</#if>

<@aui["field-wrapper"] data=data helpMessage=escape(fieldStructure.tip) label=escape(label)>
	<div class="form-group">
		${fieldStructure.children}
	</div>
</@>