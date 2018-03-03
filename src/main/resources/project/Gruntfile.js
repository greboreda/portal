module.exports = function(grunt) {

	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		less: {
			development: {
				files: {
					'../static/css/styles.css': 'less/styles.less'
				}
			}
		},
		copy: {
			login: {
				files: [{
					expand: true,
					cwd: 'src/login',
					src: '**',
					dest: '../static/js/'
				}, {
					expand: true,
					cwd: 'node_modules/bootstrap/dist/css',
					src: '**',
					dest: '../static/css/bootstrap/'
				}, {
					expand: true,
					cwd: 'node_modules/bootstrap/dist/js/',
					src: 'bootstrap.js',
					dest: '../static/js/'
				}]
			}
		}
/*
		browserify: {
			dist: {
				files: {
					'build/gol.js': 'game-of-life/index.js'
				},
				options: {
					transform: [['babelify', {'stage': 0}]],
					browserifyOptions: {
						standalone: 'gol'
					}
				}
			}
		},
*/

	});

	grunt.loadNpmTasks('grunt-contrib-less');
	//grunt.loadNpmTasks('grunt-browserify');
	grunt.loadNpmTasks('grunt-contrib-copy');
	//grunt.registerTask('default', ['browserify', 'copy:gameoflifeui', 'less']);
	grunt.registerTask('default', ['less', 'copy:login']);

};