"""ISO Uploader plugin setup."""

import os

from otopi import plugin, util

from ovirt_engine_setup import constants as osetupcons


@util.export
class Plugin(plugin.PluginBase):
    """ISO Uploader plugin setup."""

    @plugin.event(
        stage=plugin.Stages.STAGE_CLOSEUP,
        after=(
            osetupcons.Stages.DIALOG_TITLES_E_SUMMARY,
        ),
    )
    def enable_iso_uploader_plugin(self):
        os.system("chkconfig iso-uploader-plugin on")
        self.dialog.note(text="ISO Uploader plugin enabled.")
