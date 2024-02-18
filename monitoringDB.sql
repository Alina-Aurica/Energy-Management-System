PGDMP                       |         
   monitoring    16.1    16.1 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    16578 
   monitoring    DATABASE     �   CREATE DATABASE monitoring WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE monitoring;
                postgres    false            �            1259    19180    device    TABLE     �   CREATE TABLE public.device (
    maximum_hourly_energy_consumption real,
    clientid uuid,
    id uuid NOT NULL,
    address character varying(255),
    description character varying(255),
    name character varying(255)
);
    DROP TABLE public.device;
       public         heap    postgres    false            �            1259    19187    sensor    TABLE     �   CREATE TABLE public.sensor (
    total_hourly_consumption real,
    "timestamp" timestamp(6) without time zone,
    deviceid uuid,
    id uuid NOT NULL
);
    DROP TABLE public.sensor;
       public         heap    postgres    false            �          0    19180    device 
   TABLE DATA           m   COPY public.device (maximum_hourly_energy_consumption, clientid, id, address, description, name) FROM stdin;
    public          postgres    false    215   #
       �          0    19187    sensor 
   TABLE DATA           U   COPY public.sensor (total_hourly_consumption, "timestamp", deviceid, id) FROM stdin;
    public          postgres    false    216          T           2606    19186    device device_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.device
    ADD CONSTRAINT device_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.device DROP CONSTRAINT device_pkey;
       public            postgres    false    215            V           2606    19191    sensor sensor_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.sensor
    ADD CONSTRAINT sensor_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.sensor DROP CONSTRAINT sensor_pkey;
       public            postgres    false    216            �   �   x���;n�0k�<@V�gE�e�:nܺ��V K�D�?r|�)�4o0��~ѧZ�0�# �Tz��jȹZ��֩K��D�
�C]����.��C|Ѿ�F*���F���E�z�eޏ'5���,m;d�wVm}�>�Ͻ=�}>	��ٹd���d.��S/��!$W]"ǚ��w[i"��q./�q���_�I|���{�#���%��j6%�/q樂?xvS      �   �  x���A�\!E��V��
z-������=J���r`Q^��6a�# �_dO��w����OR�a �S�@�qv��3���:�oYQE�a*#,<�#�����M�K���n\�|hGnh(2�ƚ�NKs-p>L"�d�!}�lU���h�b���Ro7���^:�,�����JC@p'���Ïjd�H�n����^��%Kiqh*����|����1ӑ��i���u�O����;VK��Ū�`�6Ι���ĸUo����?�^�+Z��F�PKnW�8��*�.����Wӗ��u�P +j<3����{���N�t���@�li+	n�k�uUN��~j�P��,NCJH�C�VE��F�bu/g��*�޸���j�+x�d�@{�sa��j�/���X�<W�ۻ�O�Y�Kܫ�r�S_y�Q!a��5H{�1]���� �n�     