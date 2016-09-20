'use strict';

module.exports = function(grunt){
	//Import modules
	require('time-grunt')(grunt);
    require('jit-grunt')(grunt);

	//Main Configuration
	grunt.initConfig({
		paths: {
			src: './src/main/web-source-app/app',
			dist: './src/main/webapp'
		},
		config: {
            development: {
                options: {
                    variables: {
                        'buildType': 'development',
                        'jsSuffix': '',
                        'sassStyle': 'expanded'
                    }
                }
            },
            production: {
                options: {
                    variables: {
                        'buildType': 'production',
                        'jsSuffix': '.min',
                        'sassStyle': 'compressed'
                    }
                }
            }
        },
		clean: {
			options:{
				force: true
			},
			folder: ['<%= paths.dist %>']
		},
		bower_concat: {
			development: {
				dest: {
					js: '<%= paths.dist %>/js/_lib.js',
					css: '<%= paths.dist %>/css/_lib.css'
				},
				mainFiles: {
					'angular-ui' : ['build/angular-ui.js','build/angular-ui.css']
				}
			},
			production: {
				dest: {
					js: '<%= paths.dist %>/js/_lib.js',
					css: '<%= paths.dist %>/css/_lib.css'
				},
				mainFiles: {
					'angular-ui' : ['build/angular-ui.js','build/angular-ui.css']
				},
				callback: function(mainFiles, component) {
  							return _.map(mainFiles, function(filepath) {
    					  // Use minified files if available
    						var min = filepath.replace(/\.js$/, '.min.js');
    						return grunt.file.exists(min) ? min : filepath;
  						  });
				}
			}
		},		
		copy: {
			html_app: {
				expand: true,
				flatten: true,
				cwd: '<%= paths.src %>',
				src: ['**/*.html', '!index.html'],
				dest: '<%= paths.dist %>/html'
			},
			index: {
				src:'<%= paths.src %>/index.html', dest:'<%= paths.dist %>/index.html'
			}
		},
		concat:{
			js_App:{
				dest: '<%= paths.dist %>/js/internal.js',
				src: ['<%= paths.src %>/app.module.js','<%= paths.src %>/**/*.module.js','<%= paths.src %>/**/*.config.js','<%= paths.src %>/**/*.service.js','<%= paths.src %>/**/*.factory.js','<%= paths.src %>/**/*.controller.js']
			},
			css_App:{
				dest: '<%= paths.dist %>/css/internal.css',
				src: ['<%= paths.src %>/../resources/css/*.css']
			}
		},
		jshint: {
            options: {
                curly: true,
                eqeqeq: true,
                eqnull: true,
                undef: true,
                globals: {
                    document: true,
                    angular: true,
                    postal: true,
                    console: true,
                    alert: true
                },
                predef: ['SockJS','Stomp']
            },
            files: ['<%= paths.dist %>/js/internal.js'],
            beforeconcat: ['<%= paths.src %>/app.module.js','<%= paths.src %>/**/*.module.js','<%= paths.src %>/**/*.config.js','<%= paths.src %>/**/*.service.js','<%= paths.src %>/**/*.factory.js','<%= paths.src %>/**/*.controller.js'],
            afterconcat: ['<%= paths.dist %>/js/internal.js']
        },
		processhtml: {            
            files: {
            	'<%= paths.dist %>/index.html': '<%= paths.dist %>/index.html'
            }
        }
	});

	//Tasks
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-concat');
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-bower-concat');
	grunt.loadNpmTasks('grunt-processhtml');

	grunt.registerTask('development', [
        'clean',
        'config:development',
        'bower_concat:development',
        'copy',
        'jshint:beforeconcat',
        'concat',
        'jshint:afterconcat'
    ]);

    grunt.registerTask('production', [
        'clean',
        'config:production',
        'bower_concat:production',
        'copy',
        'jshint:beforeconcat',
        'concat',
        'jshint:afterconcat'
    ]);

    grunt.registerTask('default', ['development']);
};