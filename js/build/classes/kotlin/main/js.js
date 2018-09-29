if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'js'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'js'.");
}
var js = function (_, Kotlin) {
  'use strict';
  var Kind_INTERFACE = Kotlin.Kind.INTERFACE;
  function PlatformContract() {
  }
  PlatformContract.$metadata$ = {
    kind: Kind_INTERFACE,
    simpleName: 'PlatformContract',
    interfaces: []
  };
  var package$com = _.com || (_.com = {});
  var package$github = package$com.github || (package$com.github = {});
  var package$fcopardo = package$github.fcopardo || (package$github.fcopardo = {});
  var package$easyrest = package$fcopardo.easyrest || (package$fcopardo.easyrest = {});
  var package$api = package$easyrest.api || (package$easyrest.api = {});
  package$api.PlatformContract = PlatformContract;
  Kotlin.defineModule('js', _);
  return _;
}(typeof js === 'undefined' ? {} : js, kotlin);
