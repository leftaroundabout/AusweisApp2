import common.Library

def j = new Library
	(
		name: 'Win32_GNU_dev',
		label: "Windows7_${MERCURIAL_REVISION_BRANCH}"
	).generate(this)


j.with
{
	steps
	{
		batchFile("cd build & cmake ../source/libs -DPACKAGES_DIR=%PACKAGES_DIR% -G\"MinGW Makefiles\" -DWIN_SIGN_KEYSTORE=%WIN_SIGN_KEYSTORE% -DWIN_SIGN_KEYSTORE_PSW=%WIN_SIGN_KEYSTORE_PSW% -DWIN_SIGN_SUBJECT_NAME=%WIN_SIGN_SUBJECT_NAME%")

		shell('''\
			#!c:\\msys\\1.0\\bin\\sh --login
			cd /jenkins/$JOB_NAME/build
			mingw32-make openssl
			'''.stripIndent().trim())

		batchFile('cd build & mingw32-make compress')
	}
}
