/*
---
name: MooDialog.Alert
description: Creates an Alert dialog
authors: Arian Stolwijk
license:  MIT-style license
requires: MooDialog
provides: MooDialog.Alert
...
*/


MooDialog.Alert = new Class({

	Extends: MooDialog,

	options: {
		okText: 'Ok',
		focus: true,
		textPClass: 'MooDialogAlert'
	},

	initialize: function(msg, options){
		this.parent(options);

		var okButton = new Element('button', {
			events: {
				click: this.close.bind(this)
			},
			text: this.options.okText,
			class: 'medit-btn medit-btn-primary ripplelink cyan'
		});

		this.setContent(
			new Element('p.' + this.options.textPClass, {text: msg})
			
		);
		if (this.options.autoOpen) this.open();

		if (this.options.focus) this.addEvent('show', function(){
			okButton.focus()
		});

	}
});

