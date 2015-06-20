/**
 * 
 */
package com.zjy.baseDao;

/**
 * 
 */
public class Page extends BaseDTO {
	/**
     * 
     */
	private static final long serialVersionUID = 6874314124365508083L;
	private int showCount = 10; // 每页显示记录数
	private int totalPage; // 总页数
	private int totalResult; // 总记录数
	private int currentPage; // 当前页
	private int currentResult; // 当前记录起始索引
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	// private String orderBy;// 排序字段
	private String sort; // 排序字段

	private String order = "DESC";// 升序 降序
	// private String sort = "DESC";// 升序 降序

	private boolean isNeedPage = true;// 是否分页

	public boolean isNeedPage() {
		return isNeedPage;
	}

	public void setNeedPage(boolean isNeedPage) {
		this.isNeedPage = isNeedPage;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getTotalPage() {
		if (totalResult % showCount == 0)
			totalPage = totalResult / showCount;
		else
			totalPage = totalResult / showCount + 1;
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurrentPage() {
		if (currentPage <= 0)
			currentPage = 1;
		if (currentPage > getTotalPage())
			currentPage = getTotalPage();
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalResult > 0) {
			sb.append(" <ul>\n");
			if (currentPage == 1) {
				sb.append(" <li class=\"pageinfo\">首页</li>\n");
				sb.append(" <li class=\"pageinfo\">上页</li>\n");
			} else {
				sb.append(" <li><a href=\"#@\" onclick=\"nextPage(1)\">首页</a></li>\n");
				sb.append(" <li><a href=\"#@\" onclick=\"nextPage("
						+ (currentPage - 1) + ")\">上页</a></li>\n");
			}
			int showTag = 3; // 分页标签显示数量
			int startTag = 1;
			if (currentPage > showTag) {
				startTag = currentPage - 1;
			}
			int endTag = startTag + showTag - 1;
			for (int i = startTag; i <= totalPage && i <= endTag; i++) {
				if (currentPage == i)
					sb.append("<li class=\"current\">" + i + "</li>\n");
				else
					sb.append(" <li><a href=\"#@\" onclick=\"nextPage(" + i
							+ ")\">" + i + "</a></li>\n");
			}
			if (currentPage == totalPage) {
				sb.append(" <li class=\"pageinfo\">下页</li>\n");
				sb.append(" <li class=\"pageinfo\">尾页</li>\n");
			} else {
				sb.append(" <li><a href=\"#@\" onclick=\"nextPage("
						+ (currentPage + 1) + ")\">下页</a></li>\n");
				sb.append(" <li><a href=\"#@\" onclick=\"nextPage(" + totalPage
						+ ")\">尾页</a></li>\n");
			}
			sb.append(" <li class=\"pageinfo\">第" + currentPage + "页</li>\n");
			sb.append(" <li class=\"pageinfo\">共" + totalPage + "页</li>\n");
			sb.append("</ul>\n");
			sb.append("<script type=\"text/javascript\">\n");
			sb.append("function nextPage(page){");
			sb.append(" if(true && document.forms[0]){\n");
			sb.append("     var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("     if(url.indexOf('?')>-1){url += \"&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("     else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("     document.forms[0].action = url+page;\n");
			sb.append("     document.forms[0].submit();\n");
			sb.append(" }else{\n");
			sb.append("     var url = document.location+';\n");
			sb.append("     if(url.indexOf('?')>-1){\n");
			sb.append("         if(url.indexOf('currentPage')>-1){\n");
			sb.append("             var reg = /currentPage=\\d*/g;\n");
			sb.append("             url = url.replace(reg,'currentPage=');\n");
			sb.append("         }else{\n");
			sb.append("             url += \"&"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";\n");
			sb.append("         }\n");
			sb.append("     }else{url += \"?"
					+ (entityOrField ? "currentPage" : "page.currentPage")
					+ "=\";}\n");
			sb.append("     document.location = url + page;\n");
			sb.append(" }\n");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}

	public int getShowCount() {
		return showCount;
	}

	public void setShowCount(int showCount) {
		this.showCount = showCount;
	}

	public int getCurrentResult() {
		currentResult = (getCurrentPage() - 1) * getShowCount();
		if (currentResult < 0)
			currentResult = 0;
		return currentResult;
	}

	public void setCurrentResult(int currentResult) {
		this.currentResult = currentResult;
	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

}
