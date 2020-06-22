/**
 * Soap_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package cn.backend.service.wsdl.login;

public interface Soap_PortType extends java.rmi.Remote {

    /**
     * Login to the Salesforce.com SOAP Api
     */
    public cn.backend.service.wsdl.login.LoginResult login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.LoginFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Describe an sObject
     */
    public cn.backend.service.wsdl.login.DescribeSObjectResult describeSObject(java.lang.String sObjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe multiple sObjects (upto 100)
     */
    public cn.backend.service.wsdl.login.DescribeSObjectResult[] describeSObjects(java.lang.String[] sObjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe the Global state
     */
    public cn.backend.service.wsdl.login.DescribeGlobalResult describeGlobal() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe all the data category groups available for a given
     * set of types
     */
    public cn.backend.service.wsdl.login.DescribeDataCategoryGroupResult[] describeDataCategoryGroups(java.lang.String[] sObjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe the data category group structures for a given set
     * of pair of types and data category group name
     */
    public cn.backend.service.wsdl.login.DescribeDataCategoryGroupStructureResult[] describeDataCategoryGroupStructures(cn.backend.service.wsdl.login.DataCategoryGroupSobjectTypePair[] pairs, boolean topCategoriesOnly) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe your Data Category Mappings.
     */
    public cn.backend.service.wsdl.login.DescribeDataCategoryMappingResult[] describeDataCategoryMappings() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describes your Knowledge settings, such as if knowledgeEnabled
     * is on or off, its default language and supported languages
     */
    public cn.backend.service.wsdl.login.KnowledgeSettings describeKnowledgeSettings() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe the items in an AppMenu
     */
    public cn.backend.service.wsdl.login.DescribeAppMenuItem[] describeAppMenu(cn.backend.service.wsdl.login.AppMenuType appMenuType, java.lang.String networkId) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe Gloal and Themes
     */
    public cn.backend.service.wsdl.login.DescribeGlobalTheme describeGlobalTheme() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe Themes
     */
    public cn.backend.service.wsdl.login.DescribeThemeItem[] describeTheme(java.lang.String[] sobjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe the layout of the given sObject or the given actionable
     * global page.
     */
    public cn.backend.service.wsdl.login.DescribeLayoutResult describeLayout(java.lang.String sObjectType, java.lang.String layoutName, java.lang.String[] recordTypeIds) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Describe the layout of the SoftPhone
     */
    public cn.backend.service.wsdl.login.DescribeSoftphoneLayoutResult describeSoftphoneLayout() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe the search view of an sObject
     */
    public cn.backend.service.wsdl.login.DescribeSearchLayoutResult[] describeSearchLayouts(java.lang.String[] sObjectType) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe a list of entity names that reflects the current user's
     * searchable entities
     */
    public cn.backend.service.wsdl.login.DescribeSearchableEntityResult[] describeSearchableEntities(boolean includeOnlyEntitiesWithTabs) throws java.rmi.RemoteException;

    /**
     * Describe a list of objects representing the order and scope
     * of objects on a users search result page
     */
    public cn.backend.service.wsdl.login.DescribeSearchScopeOrderResult[] describeSearchScopeOrder(java.lang.Boolean includeRealTimeEntities) throws java.rmi.RemoteException;

    /**
     * Describe the compact layouts of the given sObject
     */
    public cn.backend.service.wsdl.login.DescribeCompactLayoutsResult describeCompactLayouts(java.lang.String sObjectType, java.lang.String[] recordTypeIds) throws java.rmi.RemoteException;

    /**
     * Describe the Path Assistants for the given sObject and optionally
     * RecordTypes
     */
    public cn.backend.service.wsdl.login.DescribePathAssistant[] describePathAssistants(java.lang.String sObjectType, java.lang.String picklistValue, java.lang.String[] recordTypeIds) throws java.rmi.RemoteException;

    /**
     * Describe the approval layouts of the given sObject
     */
    public cn.backend.service.wsdl.login.DescribeApprovalLayout[] describeApprovalLayout(java.lang.String sObjectType, java.lang.String[] approvalProcessNames) throws java.rmi.RemoteException;

    /**
     * Describe the ListViews as SOQL metadata for the generation
     * of SOQL.
     */
    public cn.backend.service.wsdl.login.DescribeSoqlListView[] describeSoqlListViews(cn.backend.service.wsdl.login.DescribeSoqlListViewParams[] request) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Execute the specified list view and return the presentation-ready
     * results.
     */
    public cn.backend.service.wsdl.login.ExecuteListViewResult executeListView(cn.backend.service.wsdl.login.ExecuteListViewRequest request) throws java.rmi.RemoteException;

    /**
     * Describe the ListViews of a SObject as SOQL metadata for the
     * generation of SOQL.
     */
    public cn.backend.service.wsdl.login.DescribeSoqlListView[] describeSObjectListViews(java.lang.String sObjectType, boolean recentsOnly, cn.backend.service.wsdl.login.ListViewIsSoqlCompatible isSoqlCompatible, int limit, int offset) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe the tabs that appear on a users page
     */
    public cn.backend.service.wsdl.login.DescribeTabSetResult[] describeTabs() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe all tabs available to a user
     */
    public cn.backend.service.wsdl.login.DescribeTab[] describeAllTabs() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Describe the primary compact layouts for the sObjects requested
     */
    public cn.backend.service.wsdl.login.DescribeCompactLayout[] describePrimaryCompactLayouts(java.lang.String[] sObjectTypes) throws java.rmi.RemoteException;

    /**
     * Create a set of new sObjects
     */
    public cn.backend.service.wsdl.login.SaveResult[] create(cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Update a set of sObjects
     */
    public cn.backend.service.wsdl.login.SaveResult[] update(cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Update or insert a set of sObjects based on object id
     */
    public cn.backend.service.wsdl.login.UpsertResult[] upsert(java.lang.String externalIDFieldName, cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Merge and update a set of sObjects based on object id
     */
    public cn.backend.service.wsdl.login.MergeResult[] merge(cn.backend.service.wsdl.login.MergeRequest[] request) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Delete a set of sObjects
     */
    public cn.backend.service.wsdl.login.DeleteResult[] delete(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Undelete a set of sObjects
     */
    public cn.backend.service.wsdl.login.UndeleteResult[] undelete(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Empty a set of sObjects from the recycle bin
     */
    public cn.backend.service.wsdl.login.EmptyRecycleBinResult[] emptyRecycleBin(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Get a set of sObjects
     */
    public cn.backend.service.wsdl.login.SObject[] retrieve(java.lang.String fieldList, java.lang.String sObjectType, java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.MalformedQueryFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Submit an entity to a workflow process or process a workitem
     */
    public cn.backend.service.wsdl.login.ProcessResult[] process(cn.backend.service.wsdl.login.ProcessRequest[] actions) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * convert a set of leads
     */
    public cn.backend.service.wsdl.login.LeadConvertResult[] convertLead(cn.backend.service.wsdl.login.LeadConvert[] leadConverts) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Logout the current user, invalidating the current session.
     */
    public void logout() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Logs out and invalidates session ids
     */
    public cn.backend.service.wsdl.login.InvalidateSessionsResult[] invalidateSessions(java.lang.String[] sessionIds) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Get the IDs for deleted sObjects
     */
    public cn.backend.service.wsdl.login.GetDeletedResult getDeleted(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Get the IDs for updated sObjects
     */
    public cn.backend.service.wsdl.login.GetUpdatedResult getUpdated(java.lang.String sObjectType, java.util.Calendar startDate, java.util.Calendar endDate) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Create a Query Cursor
     */
    public cn.backend.service.wsdl.login.QueryResult query(java.lang.String queryString) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.InvalidQueryLocatorFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.MalformedQueryFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Create a Query Cursor, including deleted sObjects
     */
    public cn.backend.service.wsdl.login.QueryResult queryAll(java.lang.String queryString) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.InvalidQueryLocatorFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.MalformedQueryFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Gets the next batch of sObjects from a query
     */
    public cn.backend.service.wsdl.login.QueryResult queryMore(java.lang.String queryLocator) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.InvalidQueryLocatorFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.MalformedQueryFault;

    /**
     * Search for sObjects
     */
    public cn.backend.service.wsdl.login.SearchResult search(java.lang.String searchString) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.MalformedSearchFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Gets server timestamp
     */
    public cn.backend.service.wsdl.login.GetServerTimestampResult getServerTimestamp() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Set a user's password
     */
    public cn.backend.service.wsdl.login.SetPasswordResult setPassword(java.lang.String userId, java.lang.String password) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidNewPasswordFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Change the current user's password
     */
    public cn.backend.service.wsdl.login.ChangeOwnPasswordResult changeOwnPassword(java.lang.String oldPassword, java.lang.String newPassword) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidNewPasswordFault, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidOldPasswordFault;

    /**
     * Reset a user's password
     */
    public cn.backend.service.wsdl.login.ResetPasswordResult resetPassword(java.lang.String userId) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Returns standard information relevant to the current user
     */
    public cn.backend.service.wsdl.login.GetUserInfoResult getUserInfo() throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Delete a set of sObjects by example. The passed SOBject is
     * a template for the object to delete
     */
    public cn.backend.service.wsdl.login.DeleteByExampleResult[] deleteByExample(cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Send existing draft EmailMessage
     */
    public cn.backend.service.wsdl.login.SendEmailResult[] sendEmailMessage(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Send outbound email
     */
    public cn.backend.service.wsdl.login.SendEmailResult[] sendEmail(cn.backend.service.wsdl.login.Email[] messages) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Perform a template merge on one or more blocks of text.
     */
    public cn.backend.service.wsdl.login.RenderEmailTemplateResult[] renderEmailTemplate(cn.backend.service.wsdl.login.RenderEmailTemplateRequest[] renderRequests) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Perform a template merge using an email template stored in
     * the database.
     */
    public cn.backend.service.wsdl.login.RenderStoredEmailTemplateResult renderStoredEmailTemplate(cn.backend.service.wsdl.login.RenderStoredEmailTemplateRequest request) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Perform a series of predefined actions such as quick create
     * or log a task
     */
    public cn.backend.service.wsdl.login.PerformQuickActionResult[] performQuickActions(cn.backend.service.wsdl.login.PerformQuickActionRequest[] quickActions) throws java.rmi.RemoteException;

    /**
     * Describe the details of a series of quick actions
     */
    public cn.backend.service.wsdl.login.DescribeQuickActionResult[] describeQuickActions(java.lang.String[] quickActions) throws java.rmi.RemoteException;

    /**
     * Describe the details of a series of quick actions in context
     * of requested recordType id for Update actions
     */
    public cn.backend.service.wsdl.login.DescribeQuickActionResult[] describeQuickActionsForRecordType(java.lang.String[] quickActions, java.lang.String recordTypeId) throws java.rmi.RemoteException;

    /**
     * Describe the details of a series of quick actions available
     * for the given contextType
     */
    public cn.backend.service.wsdl.login.DescribeAvailableQuickActionResult[] describeAvailableQuickActions(java.lang.String contextType) throws java.rmi.RemoteException;

    /**
     * Retrieve the template sobjects, if appropriate, for the given
     * quick action names in a given context
     */
    public cn.backend.service.wsdl.login.QuickActionTemplateResult[] retrieveQuickActionTemplates(java.lang.String[] quickActionNames, java.lang.String contextId) throws java.rmi.RemoteException;

    /**
     * Retrieve the template sobjects, if appropriate, for the given
     * quick action names in a given contexts when used a mass quick action
     */
    public cn.backend.service.wsdl.login.QuickActionTemplateResult[] retrieveMassQuickActionTemplates(java.lang.String quickActionName, java.lang.String[] contextIds) throws java.rmi.RemoteException;

    /**
     * Describe visualforce for an org
     */
    public cn.backend.service.wsdl.login.DescribeVisualForceResult describeVisualForce(boolean includeAllDetails, java.lang.String namespacePrefix) throws java.rmi.RemoteException;

    /**
     * Find duplicates for a set of sObjects
     */
    public cn.backend.service.wsdl.login.FindDuplicatesResult[] findDuplicates(cn.backend.service.wsdl.login.SObject[] sObjects) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.InvalidSObjectFault, cn.backend.service.wsdl.login.InvalidFieldFault, cn.backend.service.wsdl.login.UnexpectedErrorFault;

    /**
     * Find duplicates for a set of ids
     */
    public cn.backend.service.wsdl.login.FindDuplicatesResult[] findDuplicatesByIds(java.lang.String[] ids) throws java.rmi.RemoteException, cn.backend.service.wsdl.login.UnexpectedErrorFault, cn.backend.service.wsdl.login.InvalidIdFault;

    /**
     * Return the renameable nouns from the server for use in presentation
     * using the salesforce grammar engine
     */
    public cn.backend.service.wsdl.login.DescribeNounResult[] describeNouns(java.lang.String[] nouns, boolean onlyRenamed, boolean includeFields) throws java.rmi.RemoteException;
}
