from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('auth_example', '0001_initial'),
    ]

    operations = [
        migrations.DeleteModel(
            name='Account',
        ),
    ]
