module.exports = function (config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-coverage'),
      require('karma-jasmine-html-reporter'),
      require('@angular-devkit/build-angular/plugins/karma')
    ],
    client: {
      clearContext: false // laisser la sortie de Jasmine visible dans le navigateur
    },
    coverageReporter: {
      dir: require('path').join(__dirname, './coverage/pmt-frontend'),
      subdir: '.',
      reporters: [
        { type: 'html' },
        { type: 'lcovonly', subdir: 'lcov-report' },
        { type: 'text-summary' }
      ]
    },
    reporters: ['progress', 'kjhtml'],
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: true,
    browsers: ['ChromeHeadless'],  // Utilisation de ChromeHeadless pour les tests
    singleRun: false,
    restartOnFileChange: true
  });
};
