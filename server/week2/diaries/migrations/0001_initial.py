# Generated by Django 3.1.5 on 2021-01-14 09:38

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Diary',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('created', models.DateField(auto_now_add=True)),
                ('updated', models.DateTimeField(auto_now=True)),
                ('text', models.TextField()),
                ('background', models.ImageField(blank=True, null=True, upload_to='')),
                ('emotion', models.CharField(choices=[('우울', '우울'), ('분노', '분노'), ('불안', '불안'), ('고통', '고통'), ('당황', '당황'), ('행복', '행복')], default='행복', max_length=20)),
                ('emo_percent', models.IntegerField()),
            ],
            options={
                'abstract': False,
            },
        ),
    ]
