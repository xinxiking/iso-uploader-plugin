"""ovirt-host-setup dialog plugin."""


from otopi import util


from . import iusetup


@util.export
def createPlugins(context):
    iusetup.Plugin(context=context)


# vim: expandtab tabstop=4 shiftwidth=4
