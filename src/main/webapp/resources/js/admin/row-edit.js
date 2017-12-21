if (typeof Object.create !== 'function') {
    Object.create = function (obj) {
        'use strict';

        function F() {
        }

        F.prototype = obj;
        return new F();
    };
}

(function ($) {
    'use strict';

    var RowEditor = {
        init: function (options, elem) {
            var self = this;

            self.row = $(elem);

            self.options = $.extend({}, $.fn.rowEditor.options, options);
            self.isForm = !!self.row.closest('form').length;
            self.trigger = self.row.find(self.options.trigger);
            self.views = self.row.find(self.options.view);
            self.editors = self.row.find(self.options.editor);
            self.btnEdit = $(self.options.btnEdit);
            self.btnSave = $(self.options.btnSave);
            self.btnReset = $(self.options.btnReset);
            self.cells = self.isForm ? self.row : self.row.find('td');

            self.cells.each(function (i, cell) {
                cell.editor = $(cell).find(self.options.editor).find('input, select').not(':disabled'); //You can specify any excluding filter
                cell.view = $(cell).find(self.options.view);
            });

            if (!self.editors.is(':hidden')) {
                self.editors.hide();
            }

            if (self.options.setValues) {
                self.setValues();
            }

            self.buildControls();
        },

        //Create controls in row
        buildControls: function () {
            var self = this;

            self.trigger.append(self.btnEdit, self.btnSave, self.btnReset);
            self.btnSave.hide();
            self.btnReset.hide();
            self.bindEvents();
        },

        bindEvents: function () {
            var self = this;

            self.btnEdit.on('click', $.proxy(self.edit, self));
            self.btnSave.on('click', $.proxy(self.save, self));
            self.btnReset.on('click', $.proxy(self.reset, self));

            self.cells.each(function (i, cell) {
                $(cell.editor).on('keypress', function (e) {
                    if (e.keyCode === 13) {
                        self.save();
                        e.preventDefault();
                    }
                });
            });
        },

        edit: function () {
            var self = this;

            self.cells.each(function (i, cell) {
                cell.editor.removeAttr('disabled');
            });

            self.views.hide();
            self.btnEdit.hide();
            self.editors.show();
            self.btnSave.show();
            self.btnReset.show();

            //Fire onEdit callback
            if (typeof self.options.onEdit === 'function') {
                self.options.onEdit.apply(self);
            }
        },

        save: function () {
            var self = this;

            if (typeof self.options.onSave === 'function') {
                self.options.onSave.apply(self);
            }

            self.saveData().always(function () {
                self.refreshView();

                if (typeof self.options.onSaveComplete === 'function') {
                    self.options.onSaveComplete.apply(self, arguments);
                }

                self.editors.hide();
                self.btnSave.hide();
                self.btnReset.hide();
                self.views.show();
                self.btnEdit.show();
            });

            self.cells.each(function (i, cell) {
                cell.editor.attr('disabled', true);
            });
        },

        reset: function () {
            this.setValues();
        },

        refreshView: function () {
            var self = this;

            self.cells.each(function (i, cell) {
                var $editor = cell.editor,
                    val = '';

                if ($editor.is('[type="checkbox"]')) {
                    val = $editor.is(':checked') ? 'Yes' : 'No';
                } else if ($editor.is('[type="radio"]')) {
                    val = $editor.filter(':checked').val();
                } else {
                    val = $editor.val();
                }

                cell.view.text(val);
            });

            return self;
        },

        setValues: function () {
            var self = this;

            self.cells.each(function (i, cell) {
                var $editor = cell.editor,
                    val = cell.view.text();

                if ($editor.is('[type="text"], select, [type="hidden"]')) {
                    $editor.val(val);
                } else if ($editor.is('[type="radio"]')) {
                    $editor.filter('[value="' + val + '"]').attr('checked', 'checked');
                } else if ($editor.is('[type="checkbox"]') && val.toLocaleLowerCase() === 'yes') {
                    $editor.attr('checked', 'checked');
                }
            });
        },

        //Collect values from editors
        getValues: function () {
            var self = this,
                values = {};

            self.cells.each(function (i, cell) {
                var $editor = cell.editor,
                    val = null;
                console.log($editor);

                if ($editor.is('[type="text"], select, [type="hidden"]')) {
                    val = $editor.val();
                } else if ($editor.is('[type="radio"]')) {
                    val = $editor.filter(':checked').val();
                } else if ($editor.is('[type="checkbox"]')) {
                    val = $editor.is(':checked');
                }

                if ($editor.length) {
                    values[$editor.attr('name')] = val;
                }
            });

            return values;
        },

        //Send row data to server
        saveData: function () {
            var self = this;

            return $.ajax({
                method: 'POST',
                url: self.options.apiUrl,
                data: self.getValues()
            });
        }
    };

    $.fn.rowEditor = function (options) {
        return this.each(function () {
            var rowEditor = Object.create(RowEditor);
            rowEditor.init(options, this);
            $.data(this, 'rowEditor', rowEditor);
        });
    };

    $.fn.rowEditor.options = {
        editor: '.editor',
        view: '.view',
        trigger: '.trigger',
        btnEdit: '<input type="button" class="btn btn-large btn-success" value="Edit">',
        btnSave: '<input type="button" class="btn btn-large btn-success" value="Save" style="width: 80px;">',
        btnReset: '<input type="button" class="btn btn-large btn-danger" value="Reset" style="width: 80px;">',
        apiUrl: '',
        setValues: true,
        onEdit: null,
        onSave: null,
        onSaveComplete: null
    };
})(jQuery);
