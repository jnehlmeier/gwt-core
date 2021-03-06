# gwt-core

This project is meant to roughly approximate the various classes and methods found in gwt-user's `com.google.gwt.core`
packages. In some cases, updated replacements are provided, in other cases working, but deprecated versions are offered,
and further classes or members are outright skipped, encouraging projects to either find an alternative way to manage
these operations before updating, or switch to a GWT3-version while performing the actual migration.

This project is not yet complete, there are doubtlessly better ways to handle some cases here.


### Listing of classes from gwt-user's core

 * client
   * debug
     * JsoInspector - Skipped, at least for now, I'm not aware of any developer tools actually making use of this
   * impl
     * AsyncFragmentLoader - Skipped, used primarily by Split Points, not yet present in GWT3
     * CrossSiteLoadingStrategy - Skipped, split points
     * Impl - Skipped, internal details of GWT2, cannot be expected to exist at all in GWT3
     * JavaScriptExceptionBase - Skipped, exceptions are handled differently in GWT3, legacy dev mode is not a concern
     * LoadingStrategyBase - Skipped, split points
     * OnSuccessExecutor - Skipped, package-protected and used in split points
     * SchedulerImpl - Updated, missing only scheduleEntry
     * ScriptTagLoadingStrategy - Skipped, split points
     * StackTraceCreator - Skipped, browsers are less crappy now
     * SuperDevModeLogger - Skipped, GWT.log either will not exist, or will only be implemented in this way anyway
     * SynchronousOnSuccessExecutor - Skipped, package-protected, split points
     * WeakMapping - TODO consider providing this, consider an approach similar to how J2CL handles hashcodes
     * XhrLoadingStrategy - Skipped, split points
   * prefetch - package skipped, split points
   * testing
     * StubScheduler - included, deliberately skipping scheduleEntry to match ScheduleImpl
   * AsyncProvided - Skipped, split points
   * Callback - Included, simple interface that eventually probably should be moved anyway
   * CodeDownloadExecution - Skipped, split points
   * Duration - Included for now, only as a util class for Scheduler
   * EntryPoint - Included, applications will likely still use this as a way to start up
   * GWT - Partially included for now to ease migration. This likely belongs in the "third" category, code which largely
   will not exist in GWT 3, so should be removed now, but this is used so extensively that would be difficult.
   * JavaScriptException - Skipped for now, unsure we can replace in a way that will still behave the same as before
   * JavaScriptObject - Included, deprecated: classes extending this will need additional updates to properly conform
   to JsInterop requirements
   * JsArray - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<T>`
   * JsArrayBoolean - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<Boolean>`
   * JsArrayInteger - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<Double>`
   and cast or coerce appropriately
   * JsArrayMixed - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<Any>` or
   use `getAnyAt(int)`
   * JsArrayNumber - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<Double>`
   * JsArrayString - Updated, deprecated: projects should migrate when feasible to `elemental2.core.JsArray<JsString>`
   * JsArrayUtils - Updated, deprecated, consists only of calls to Js.uncheckedCast
   * JsDate -
   * JsonUtils -
   * RunAsyncCallback - Skipped, split points
   * Scheduler - Updated, mising only scheduleEntry
   * ScriptInjector - Updated
   * SingleJsoImpl - Skipped, JSO-related, shouldn't be needed
   * SingleJsoImplName - Skipped, JSO-related, shouldn't be needed
   
   
As with other ported modules, the expectation for the initial release is to as closely match as possible the API of the
original code, minus package structure, even when a better replacement exists. This will enable projects to first
migrate to the new packages, then later update to newer versions of the migrated modules where further changes and
improvements are made.


### Updating JavaScriptObject subclasses
TODO