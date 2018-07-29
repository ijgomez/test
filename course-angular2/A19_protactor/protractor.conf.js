let SpecReporter = require('jasmine-spec-reporter').SpecReporter;
// https://github.com/angular/protractor/issues/1451
require('protractor/built/logger').Logger.logLevel = 1;

exports.config = {
  seleniumAddress: 'http://localhost:4444/wd/hub',
  framework: 'jasmine2',
  jasmineNodeOpts: {
    showColors: true,
    silent: true,
    defaultTimeoutInterval: 360000,
    print: function () {
    }
  },
  specs: ['e2e/listado.spec.js'],
  capabilities: {
    browserName: 'chrome',
    'chromeOptions': {
      args: ['--test-type']
    }
  },
  onPrepare: function () {
    jasmine.getEnv().addReporter(new SpecReporter({
      spec: {
        displayStacktrace: true
      },
      summary: {
        displayDuration: false
      }
    }));
  }
};
