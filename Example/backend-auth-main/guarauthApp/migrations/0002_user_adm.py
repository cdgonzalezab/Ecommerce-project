# Generated by Django 3.2.7 on 2021-11-27 01:01

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('guarauthApp', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='user',
            name='adm',
            field=models.BooleanField(default=False, verbose_name='admin'),
        ),
    ]
