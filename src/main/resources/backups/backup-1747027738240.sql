PGDMP  :    :    	            }            DocumentMIS    16.8    16.8 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    19470    DocumentMIS    DATABASE     s   CREATE DATABASE "DocumentMIS" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en-US';
    DROP DATABASE "DocumentMIS";
                postgres    false            �            1259    19471    activity_log    TABLE     E  CREATE TABLE public.activity_log (
    id bigint NOT NULL,
    action character varying(255),
    content character varying(6000),
    department_name character varying(255),
    entity_name character varying(255),
    record_id bigint,
    "timestamp" timestamp(6) without time zone,
    user_name character varying(255)
);
     DROP TABLE public.activity_log;
       public         heap    postgres    false            �            1259    19476    activity_log_department_list    TABLE     �   CREATE TABLE public.activity_log_department_list (
    activity_log_id bigint NOT NULL,
    department_list_dep_id bigint NOT NULL
);
 0   DROP TABLE public.activity_log_department_list;
       public         heap    postgres    false            �            1259    19479    activity_log_id_seq    SEQUENCE     |   CREATE SEQUENCE public.activity_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.activity_log_id_seq;
       public          postgres    false    215            �           0    0    activity_log_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.activity_log_id_seq OWNED BY public.activity_log.id;
          public          postgres    false    217            �            1259    19480    appendant_docs    TABLE     �   CREATE TABLE public.appendant_docs (
    appendant_doc_id bigint NOT NULL,
    appendant_doc_download_url character varying(255),
    appendant_doc_name character varying(255),
    appendant_doc_path character varying(255),
    doc_id bigint
);
 "   DROP TABLE public.appendant_docs;
       public         heap    postgres    false            �            1259    19485 #   appendant_docs_appendant_doc_id_seq    SEQUENCE     �   CREATE SEQUENCE public.appendant_docs_appendant_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 :   DROP SEQUENCE public.appendant_docs_appendant_doc_id_seq;
       public          postgres    false    218            �           0    0 #   appendant_docs_appendant_doc_id_seq    SEQUENCE OWNED BY     k   ALTER SEQUENCE public.appendant_docs_appendant_doc_id_seq OWNED BY public.appendant_docs.appendant_doc_id;
          public          postgres    false    219            �            1259    19486    backupdb    TABLE     �   CREATE TABLE public.backupdb (
    id bigint NOT NULL,
    backup_path character varying(255),
    created_at timestamp(6) without time zone,
    user_id bigint
);
    DROP TABLE public.backupdb;
       public         heap    postgres    false            �            1259    19489    backupdb_id_seq    SEQUENCE     x   CREATE SEQUENCE public.backupdb_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.backupdb_id_seq;
       public          postgres    false    220            �           0    0    backupdb_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.backupdb_id_seq OWNED BY public.backupdb.id;
          public          postgres    false    221            �            1259    19490    comment    TABLE     L  CREATE TABLE public.comment (
    comment_id bigint NOT NULL,
    comment_date_time timestamp(6) without time zone,
    comment_text character varying(255),
    status timestamp(6) without time zone,
    receiver_department bigint,
    document_id bigint,
    parent_comment_id bigint,
    send_doc_id bigint,
    user_id bigint
);
    DROP TABLE public.comment;
       public         heap    postgres    false            �            1259    19493    comment_comment_id_seq    SEQUENCE        CREATE SEQUENCE public.comment_comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.comment_comment_id_seq;
       public          postgres    false    222            �           0    0    comment_comment_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.comment_comment_id_seq OWNED BY public.comment.comment_id;
          public          postgres    false    223            �            1259    19494 
   department    TABLE     �   CREATE TABLE public.department (
    dep_id bigint NOT NULL,
    dep_name character varying(255),
    description character varying(255),
    parent_dep_id bigint
);
    DROP TABLE public.department;
       public         heap    postgres    false            �            1259    19499    department_dep_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.department_dep_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.department_dep_id_seq;
       public          postgres    false    224            �           0    0    department_dep_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.department_dep_id_seq OWNED BY public.department.dep_id;
          public          postgres    false    225            �            1259    19500    doc_reference    TABLE     �   CREATE TABLE public.doc_reference (
    id bigint NOT NULL,
    ref_name character varying(255),
    description character varying(255),
    name character varying(255)
);
 !   DROP TABLE public.doc_reference;
       public         heap    postgres    false            �            1259    19505    doc_reference_id_seq    SEQUENCE     }   CREATE SEQUENCE public.doc_reference_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.doc_reference_id_seq;
       public          postgres    false    226            �           0    0    doc_reference_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.doc_reference_id_seq OWNED BY public.doc_reference.id;
          public          postgres    false    227            �            1259    19506 
   doc_report    TABLE       CREATE TABLE public.doc_report (
    doc_report_id bigint NOT NULL,
    action character varying(4000),
    date timestamp(6) without time zone,
    doc_path character varying(255),
    doc_status smallint,
    download_url character varying(255),
    findings character varying(6000),
    report_title character varying(255),
    target_organ_response character varying(4000),
    send_doc_send_doc_id bigint,
    user_id bigint,
    CONSTRAINT doc_report_doc_status_check CHECK (((doc_status >= 0) AND (doc_status <= 4)))
);
    DROP TABLE public.doc_report;
       public         heap    postgres    false            �            1259    19512    doc_report_doc_report_id_seq    SEQUENCE     �   CREATE SEQUENCE public.doc_report_doc_report_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.doc_report_doc_report_id_seq;
       public          postgres    false    228            �           0    0    doc_report_doc_report_id_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.doc_report_doc_report_id_seq OWNED BY public.doc_report.doc_report_id;
          public          postgres    false    229            �            1259    19513    document    TABLE     �  CREATE TABLE public.document (
    doc_id bigint NOT NULL,
    creation_date date,
    deadline date,
    description character varying(3000),
    doc_name character varying(255),
    doc_number character varying(255),
    doc_number2 character varying(255),
    doc_path character varying(255),
    doc_type character varying(255),
    download_url character varying(255),
    execution_type character varying(255),
    initial_date date,
    received_date date,
    reference_type character varying(255),
    second_date date,
    subject character varying(255),
    update_date date,
    department bigint,
    ref_id bigint,
    user_id bigint,
    CONSTRAINT document_doc_type_check CHECK (((doc_type)::text = ANY (ARRAY[('MAKTOOB'::character varying)::text, ('FARMAN'::character varying)::text, ('MUSAWWIBA'::character varying)::text, ('HIDAYAT'::character varying)::text, ('HUKAM'::character varying)::text, ('REPORT'::character varying)::text]))),
    CONSTRAINT document_execution_type_check CHECK (((execution_type)::text = ANY (ARRAY[('CONTINUOUS'::character varying)::text, ('INFORMATIVE'::character varying)::text, ('RESULT_BASE'::character varying)::text]))),
    CONSTRAINT document_reference_type_check CHECK (((reference_type)::text = ANY (ARRAY[('AMIR'::character varying)::text, ('RAYESULWOZARA'::character varying)::text, ('KABINA'::character varying)::text, ('FINANCE_KABINA'::character varying)::text, ('OTHER'::character varying)::text])))
);
    DROP TABLE public.document;
       public         heap    postgres    false            �            1259    19521    document_doc_id_seq    SEQUENCE     |   CREATE SEQUENCE public.document_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.document_doc_id_seq;
       public          postgres    false    230            �           0    0    document_doc_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.document_doc_id_seq OWNED BY public.document.doc_id;
          public          postgres    false    231            �            1259    19522    linking_doc    TABLE     �   CREATE TABLE public.linking_doc (
    id bigint NOT NULL,
    create_date date,
    first bigint,
    second bigint,
    department_dep_id bigint,
    user_id bigint
);
    DROP TABLE public.linking_doc;
       public         heap    postgres    false            �            1259    19525    linking_doc_id_seq    SEQUENCE     {   CREATE SEQUENCE public.linking_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.linking_doc_id_seq;
       public          postgres    false    232            �           0    0    linking_doc_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.linking_doc_id_seq OWNED BY public.linking_doc.id;
          public          postgres    false    233            �            1259    19526    notification    TABLE       CREATE TABLE public.notification (
    id bigint NOT NULL,
    content character varying(5000),
    created_at timestamp(6) without time zone,
    notification_type character varying(255),
    read_at timestamp(6) without time zone,
    recipient bigint
);
     DROP TABLE public.notification;
       public         heap    postgres    false            �            1259    19531    notification_id_seq    SEQUENCE     |   CREATE SEQUENCE public.notification_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.notification_id_seq;
       public          postgres    false    234            �           0    0    notification_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.notification_id_seq OWNED BY public.notification.id;
          public          postgres    false    235            �            1259    19532 
   permission    TABLE     �   CREATE TABLE public.permission (
    id bigint NOT NULL,
    dr_name character varying(255),
    en_name character varying(255),
    permission_name character varying(255),
    ps_name character varying(255)
);
    DROP TABLE public.permission;
       public         heap    postgres    false            �            1259    19537    permission_id_seq    SEQUENCE     z   CREATE SEQUENCE public.permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.permission_id_seq;
       public          postgres    false    236            �           0    0    permission_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.permission_id_seq OWNED BY public.permission.id;
          public          postgres    false    237            �            1259    19538    role_permissions    TABLE     i   CREATE TABLE public.role_permissions (
    role_id bigint NOT NULL,
    permission_id bigint NOT NULL
);
 $   DROP TABLE public.role_permissions;
       public         heap    postgres    false            �            1259    19541    roles    TABLE     �   CREATE TABLE public.roles (
    id bigint NOT NULL,
    description character varying(255),
    role_name character varying(255)
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    19546    roles_id_seq    SEQUENCE     u   CREATE SEQUENCE public.roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.roles_id_seq;
       public          postgres    false    239            �           0    0    roles_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;
          public          postgres    false    240            �            1259    19547    send_doc    TABLE     8  CREATE TABLE public.send_doc (
    send_doc_id bigint NOT NULL,
    action character varying(255),
    ancestor character varying(255),
    doc_status character varying(255),
    findings character varying(255),
    guide character varying(255),
    secret character varying(255),
    send_appendent_docs boolean DEFAULT true NOT NULL,
    send_orginal_doc boolean DEFAULT true NOT NULL,
    sending_date timestamp(6) without time zone,
    sending_status character varying(255),
    target_organ_response character varying(255),
    time_to_seen timestamp(6) without time zone,
    doc_id bigint,
    parent_send_doc_id bigint,
    receiver_department_dep_id bigint,
    sender_department_dep_id bigint,
    sender_userid_id bigint,
    CONSTRAINT send_doc_doc_status_check CHECK (((doc_status)::text = ANY (ARRAY[('TODO'::character varying)::text, ('IN_PROGRESS'::character varying)::text, ('DONE'::character varying)::text, ('IN_COMPLETE'::character varying)::text, ('VIOLATION'::character varying)::text]))),
    CONSTRAINT send_doc_secret_check CHECK (((secret)::text = ANY (ARRAY[('SECRET'::character varying)::text, ('NON_SECRET'::character varying)::text]))),
    CONSTRAINT send_doc_sending_status_check CHECK (((sending_status)::text = ANY (ARRAY[('PENDING'::character varying)::text, ('SEEN'::character varying)::text])))
);
    DROP TABLE public.send_doc;
       public         heap    postgres    false            �            1259    19557    send_doc_send_doc_id_seq    SEQUENCE     �   CREATE SEQUENCE public.send_doc_send_doc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.send_doc_send_doc_id_seq;
       public          postgres    false    241            �           0    0    send_doc_send_doc_id_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.send_doc_send_doc_id_seq OWNED BY public.send_doc.send_doc_id;
          public          postgres    false    242            �            1259    19558    token    TABLE     *  CREATE TABLE public.token (
    token_id bigint NOT NULL,
    expired boolean NOT NULL,
    revoked boolean NOT NULL,
    token character varying(255),
    token_type character varying(255),
    user_id bigint,
    CONSTRAINT token_token_type_check CHECK (((token_type)::text = 'BEARER'::text))
);
    DROP TABLE public.token;
       public         heap    postgres    false            �            1259    19564    token_token_id_seq    SEQUENCE     {   CREATE SEQUENCE public.token_token_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.token_token_id_seq;
       public          postgres    false    243            �           0    0    token_token_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.token_token_id_seq OWNED BY public.token.token_id;
          public          postgres    false    244            �            1259    19565    user-department    TABLE     n   CREATE TABLE public."user-department" (
    "user-id" bigint NOT NULL,
    "department-id" bigint NOT NULL
);
 %   DROP TABLE public."user-department";
       public         heap    postgres    false            �            1259    19568 
   user-roles    TABLE     c   CREATE TABLE public."user-roles" (
    "user-id" bigint NOT NULL,
    "role-id" bigint NOT NULL
);
     DROP TABLE public."user-roles";
       public         heap    postgres    false            �            1259    19571    users    TABLE     �  CREATE TABLE public.users (
    id bigint NOT NULL,
    activate boolean NOT NULL,
    downloadurl character varying(255),
    email character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    otp_code character varying(255),
    otp_expiration timestamp(6) without time zone,
    password character varying(255),
    phone_no character varying(255),
    "position" character varying(255),
    profile_path character varying(255),
    user_type character varying(255),
    CONSTRAINT users_user_type_check CHECK (((user_type)::text = ANY (ARRAY[('ADMIN'::character varying)::text, ('MEMBER_OF_COMMITTEE'::character varying)::text, ('MEMBER_OF_DEPARTMENT'::character varying)::text])))
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    19577    users_id_seq    SEQUENCE     u   CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    247            �           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    248            �           2604    24798    activity_log id    DEFAULT     r   ALTER TABLE ONLY public.activity_log ALTER COLUMN id SET DEFAULT nextval('public.activity_log_id_seq'::regclass);
 >   ALTER TABLE public.activity_log ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    215            �           2604    24799    appendant_docs appendant_doc_id    DEFAULT     �   ALTER TABLE ONLY public.appendant_docs ALTER COLUMN appendant_doc_id SET DEFAULT nextval('public.appendant_docs_appendant_doc_id_seq'::regclass);
 N   ALTER TABLE public.appendant_docs ALTER COLUMN appendant_doc_id DROP DEFAULT;
       public          postgres    false    219    218            �           2604    24800    backupdb id    DEFAULT     j   ALTER TABLE ONLY public.backupdb ALTER COLUMN id SET DEFAULT nextval('public.backupdb_id_seq'::regclass);
 :   ALTER TABLE public.backupdb ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220            �           2604    24801    comment comment_id    DEFAULT     x   ALTER TABLE ONLY public.comment ALTER COLUMN comment_id SET DEFAULT nextval('public.comment_comment_id_seq'::regclass);
 A   ALTER TABLE public.comment ALTER COLUMN comment_id DROP DEFAULT;
       public          postgres    false    223    222            �           2604    24802    department dep_id    DEFAULT     v   ALTER TABLE ONLY public.department ALTER COLUMN dep_id SET DEFAULT nextval('public.department_dep_id_seq'::regclass);
 @   ALTER TABLE public.department ALTER COLUMN dep_id DROP DEFAULT;
       public          postgres    false    225    224            �           2604    24803    doc_reference id    DEFAULT     t   ALTER TABLE ONLY public.doc_reference ALTER COLUMN id SET DEFAULT nextval('public.doc_reference_id_seq'::regclass);
 ?   ALTER TABLE public.doc_reference ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    227    226            �           2604    24804    doc_report doc_report_id    DEFAULT     �   ALTER TABLE ONLY public.doc_report ALTER COLUMN doc_report_id SET DEFAULT nextval('public.doc_report_doc_report_id_seq'::regclass);
 G   ALTER TABLE public.doc_report ALTER COLUMN doc_report_id DROP DEFAULT;
       public          postgres    false    229    228            �           2604    24805    document doc_id    DEFAULT     r   ALTER TABLE ONLY public.document ALTER COLUMN doc_id SET DEFAULT nextval('public.document_doc_id_seq'::regclass);
 >   ALTER TABLE public.document ALTER COLUMN doc_id DROP DEFAULT;
       public          postgres    false    231    230            �           2604    24806    linking_doc id    DEFAULT     p   ALTER TABLE ONLY public.linking_doc ALTER COLUMN id SET DEFAULT nextval('public.linking_doc_id_seq'::regclass);
 =   ALTER TABLE public.linking_doc ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    232            �           2604    24807    notification id    DEFAULT     r   ALTER TABLE ONLY public.notification ALTER COLUMN id SET DEFAULT nextval('public.notification_id_seq'::regclass);
 >   ALTER TABLE public.notification ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    235    234            �           2604    24808    permission id    DEFAULT     n   ALTER TABLE ONLY public.permission ALTER COLUMN id SET DEFAULT nextval('public.permission_id_seq'::regclass);
 <   ALTER TABLE public.permission ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    237    236            �           2604    24809    roles id    DEFAULT     d   ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);
 7   ALTER TABLE public.roles ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    240    239            �           2604    24810    send_doc send_doc_id    DEFAULT     |   ALTER TABLE ONLY public.send_doc ALTER COLUMN send_doc_id SET DEFAULT nextval('public.send_doc_send_doc_id_seq'::regclass);
 C   ALTER TABLE public.send_doc ALTER COLUMN send_doc_id DROP DEFAULT;
       public          postgres    false    242    241            �           2604    24811    token token_id    DEFAULT     p   ALTER TABLE ONLY public.token ALTER COLUMN token_id SET DEFAULT nextval('public.token_token_id_seq'::regclass);
 =   ALTER TABLE public.token ALTER COLUMN token_id DROP DEFAULT;
       public          postgres    false    244    243            �           2604    24812    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    248    247            �          0    19471    activity_log 
   TABLE DATA           |   COPY public.activity_log (id, action, content, department_name, entity_name, record_id, "timestamp", user_name) FROM stdin;
    public          postgres    false    215   ��       �          0    19476    activity_log_department_list 
   TABLE DATA           _   COPY public.activity_log_department_list (activity_log_id, department_list_dep_id) FROM stdin;
    public          postgres    false    216   N�       �          0    19480    appendant_docs 
   TABLE DATA           �   COPY public.appendant_docs (appendant_doc_id, appendant_doc_download_url, appendant_doc_name, appendant_doc_path, doc_id) FROM stdin;
    public          postgres    false    218   ��       �          0    19486    backupdb 
   TABLE DATA           H   COPY public.backupdb (id, backup_path, created_at, user_id) FROM stdin;
    public          postgres    false    220   ��       �          0    19490    comment 
   TABLE DATA           �   COPY public.comment (comment_id, comment_date_time, comment_text, status, receiver_department, document_id, parent_comment_id, send_doc_id, user_id) FROM stdin;
    public          postgres    false    222   �       �          0    19494 
   department 
   TABLE DATA           R   COPY public.department (dep_id, dep_name, description, parent_dep_id) FROM stdin;
    public          postgres    false    224   ��       �          0    19500    doc_reference 
   TABLE DATA           H   COPY public.doc_reference (id, ref_name, description, name) FROM stdin;
    public          postgres    false    226   �       �          0    19506 
   doc_report 
   TABLE DATA           �   COPY public.doc_report (doc_report_id, action, date, doc_path, doc_status, download_url, findings, report_title, target_organ_response, send_doc_send_doc_id, user_id) FROM stdin;
    public          postgres    false    228   <�       �          0    19513    document 
   TABLE DATA             COPY public.document (doc_id, creation_date, deadline, description, doc_name, doc_number, doc_number2, doc_path, doc_type, download_url, execution_type, initial_date, received_date, reference_type, second_date, subject, update_date, department, ref_id, user_id) FROM stdin;
    public          postgres    false    230   �       �          0    19522    linking_doc 
   TABLE DATA           a   COPY public.linking_doc (id, create_date, first, second, department_dep_id, user_id) FROM stdin;
    public          postgres    false    232   ��       �          0    19526    notification 
   TABLE DATA           f   COPY public.notification (id, content, created_at, notification_type, read_at, recipient) FROM stdin;
    public          postgres    false    234   ��       �          0    19532 
   permission 
   TABLE DATA           T   COPY public.permission (id, dr_name, en_name, permission_name, ps_name) FROM stdin;
    public          postgres    false    236   k�       �          0    19538    role_permissions 
   TABLE DATA           B   COPY public.role_permissions (role_id, permission_id) FROM stdin;
    public          postgres    false    238   ��       �          0    19541    roles 
   TABLE DATA           ;   COPY public.roles (id, description, role_name) FROM stdin;
    public          postgres    false    239   i�       �          0    19547    send_doc 
   TABLE DATA           4  COPY public.send_doc (send_doc_id, action, ancestor, doc_status, findings, guide, secret, send_appendent_docs, send_orginal_doc, sending_date, sending_status, target_organ_response, time_to_seen, doc_id, parent_send_doc_id, receiver_department_dep_id, sender_department_dep_id, sender_userid_id) FROM stdin;
    public          postgres    false    241   ��       �          0    19558    token 
   TABLE DATA           W   COPY public.token (token_id, expired, revoked, token, token_type, user_id) FROM stdin;
    public          postgres    false    243   �       �          0    19565    user-department 
   TABLE DATA           G   COPY public."user-department" ("user-id", "department-id") FROM stdin;
    public          postgres    false    245   �      �          0    19568 
   user-roles 
   TABLE DATA           <   COPY public."user-roles" ("user-id", "role-id") FROM stdin;
    public          postgres    false    246   "      �          0    19571    users 
   TABLE DATA           �   COPY public.users (id, activate, downloadurl, email, first_name, last_name, otp_code, otp_expiration, password, phone_no, "position", profile_path, user_type) FROM stdin;
    public          postgres    false    247   S      �           0    0    activity_log_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.activity_log_id_seq', 263, true);
          public          postgres    false    217            �           0    0 #   appendant_docs_appendant_doc_id_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.appendant_docs_appendant_doc_id_seq', 4, true);
          public          postgres    false    219            �           0    0    backupdb_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.backupdb_id_seq', 4, true);
          public          postgres    false    221            �           0    0    comment_comment_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.comment_comment_id_seq', 7, true);
          public          postgres    false    223            �           0    0    department_dep_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.department_dep_id_seq', 18, true);
          public          postgres    false    225            �           0    0    doc_reference_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.doc_reference_id_seq', 1, false);
          public          postgres    false    227            �           0    0    doc_report_doc_report_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.doc_report_doc_report_id_seq', 4, true);
          public          postgres    false    229            �           0    0    document_doc_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.document_doc_id_seq', 14, true);
          public          postgres    false    231            �           0    0    linking_doc_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.linking_doc_id_seq', 1, false);
          public          postgres    false    233            �           0    0    notification_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.notification_id_seq', 29, true);
          public          postgres    false    235            �           0    0    permission_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.permission_id_seq', 45, true);
          public          postgres    false    237            �           0    0    roles_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.roles_id_seq', 3, true);
          public          postgres    false    240            �           0    0    send_doc_send_doc_id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public.send_doc_send_doc_id_seq', 31, true);
          public          postgres    false    242            �           0    0    token_token_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.token_token_id_seq', 210, true);
          public          postgres    false    244            �           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 5, true);
          public          postgres    false    248            �           2606    19594    activity_log activity_log_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.activity_log
    ADD CONSTRAINT activity_log_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.activity_log DROP CONSTRAINT activity_log_pkey;
       public            postgres    false    215            �           2606    19596 "   appendant_docs appendant_docs_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY public.appendant_docs
    ADD CONSTRAINT appendant_docs_pkey PRIMARY KEY (appendant_doc_id);
 L   ALTER TABLE ONLY public.appendant_docs DROP CONSTRAINT appendant_docs_pkey;
       public            postgres    false    218            �           2606    19598    backupdb backupdb_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.backupdb
    ADD CONSTRAINT backupdb_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.backupdb DROP CONSTRAINT backupdb_pkey;
       public            postgres    false    220            �           2606    19600    comment comment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (comment_id);
 >   ALTER TABLE ONLY public.comment DROP CONSTRAINT comment_pkey;
       public            postgres    false    222            �           2606    19602    department department_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.department
    ADD CONSTRAINT department_pkey PRIMARY KEY (dep_id);
 D   ALTER TABLE ONLY public.department DROP CONSTRAINT department_pkey;
       public            postgres    false    224            �           2606    19604     doc_reference doc_reference_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.doc_reference
    ADD CONSTRAINT doc_reference_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.doc_reference DROP CONSTRAINT doc_reference_pkey;
       public            postgres    false    226            �           2606    19606    doc_report doc_report_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.doc_report
    ADD CONSTRAINT doc_report_pkey PRIMARY KEY (doc_report_id);
 D   ALTER TABLE ONLY public.doc_report DROP CONSTRAINT doc_report_pkey;
       public            postgres    false    228            �           2606    19608    document document_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.document
    ADD CONSTRAINT document_pkey PRIMARY KEY (doc_id);
 @   ALTER TABLE ONLY public.document DROP CONSTRAINT document_pkey;
       public            postgres    false    230            �           2606    19610    linking_doc linking_doc_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.linking_doc
    ADD CONSTRAINT linking_doc_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.linking_doc DROP CONSTRAINT linking_doc_pkey;
       public            postgres    false    232            �           2606    19612    notification notification_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.notification
    ADD CONSTRAINT notification_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.notification DROP CONSTRAINT notification_pkey;
       public            postgres    false    234            �           2606    19614    permission permission_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.permission
    ADD CONSTRAINT permission_pkey PRIMARY KEY (id);
 D   ALTER TABLE ONLY public.permission DROP CONSTRAINT permission_pkey;
       public            postgres    false    236            �           2606    19616    roles roles_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    239            �           2606    19618    send_doc send_doc_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT send_doc_pkey PRIMARY KEY (send_doc_id);
 @   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT send_doc_pkey;
       public            postgres    false    241            �           2606    19620    token token_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.token
    ADD CONSTRAINT token_pkey PRIMARY KEY (token_id);
 :   ALTER TABLE ONLY public.token DROP CONSTRAINT token_pkey;
       public            postgres    false    243            �           2606    19622 "   users uk_6dotkott2kjsp8vw4d0m25fb7 
   CONSTRAINT     ^   ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);
 L   ALTER TABLE ONLY public.users DROP CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7;
       public            postgres    false    247            �           2606    19624 "   token uk_pddrhgwxnms2aceeku9s2ewy5 
   CONSTRAINT     ^   ALTER TABLE ONLY public.token
    ADD CONSTRAINT uk_pddrhgwxnms2aceeku9s2ewy5 UNIQUE (token);
 L   ALTER TABLE ONLY public.token DROP CONSTRAINT uk_pddrhgwxnms2aceeku9s2ewy5;
       public            postgres    false    243            �           2606    19626    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    247            �           2606    19627 (   notification fk1pllq3ww34eskdn6smt5vtbk7    FK CONSTRAINT     �   ALTER TABLE ONLY public.notification
    ADD CONSTRAINT fk1pllq3ww34eskdn6smt5vtbk7 FOREIGN KEY (recipient) REFERENCES public.users(id);
 R   ALTER TABLE ONLY public.notification DROP CONSTRAINT fk1pllq3ww34eskdn6smt5vtbk7;
       public          postgres    false    234    4833    247            �           2606    19632 '   linking_doc fk1qj0kbbd2e7d3p3tbg3ht4x9d    FK CONSTRAINT     �   ALTER TABLE ONLY public.linking_doc
    ADD CONSTRAINT fk1qj0kbbd2e7d3p3tbg3ht4x9d FOREIGN KEY (department_dep_id) REFERENCES public.department(dep_id);
 Q   ALTER TABLE ONLY public.linking_doc DROP CONSTRAINT fk1qj0kbbd2e7d3p3tbg3ht4x9d;
       public          postgres    false    224    232    4809            �           2606    19637 #   comment fk29x8bst9rb3bcv6n4x9ypg3ip    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fk29x8bst9rb3bcv6n4x9ypg3ip FOREIGN KEY (send_doc_id) REFERENCES public.send_doc(send_doc_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fk29x8bst9rb3bcv6n4x9ypg3ip;
       public          postgres    false    222    4825    241            �           2606    19642 +   user-department fk37f5twm0vgm8bl7fyqon1b1i8    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-department"
    ADD CONSTRAINT fk37f5twm0vgm8bl7fyqon1b1i8 FOREIGN KEY ("department-id") REFERENCES public.department(dep_id);
 W   ALTER TABLE ONLY public."user-department" DROP CONSTRAINT fk37f5twm0vgm8bl7fyqon1b1i8;
       public          postgres    false    245    4809    224            �           2606    19647 $   send_doc fk39y1mjmnkouaq4an05qtcrq3x    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fk39y1mjmnkouaq4an05qtcrq3x FOREIGN KEY (receiver_department_dep_id) REFERENCES public.department(dep_id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fk39y1mjmnkouaq4an05qtcrq3x;
       public          postgres    false    241    4809    224            �           2606    19652 $   send_doc fk3icem1p54fr3dm92499sw00b8    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fk3icem1p54fr3dm92499sw00b8 FOREIGN KEY (sender_userid_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fk3icem1p54fr3dm92499sw00b8;
       public          postgres    false    241    4833    247            �           2606    19657 &   user-roles fk5badaua3hwxpji2rnw94c3kui    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-roles"
    ADD CONSTRAINT fk5badaua3hwxpji2rnw94c3kui FOREIGN KEY ("user-id") REFERENCES public.users(id);
 R   ALTER TABLE ONLY public."user-roles" DROP CONSTRAINT fk5badaua3hwxpji2rnw94c3kui;
       public          postgres    false    246    247    4833            �           2606    19662 &   doc_report fk6i2kjuplgrh2mlrt79nb8yg0s    FK CONSTRAINT     �   ALTER TABLE ONLY public.doc_report
    ADD CONSTRAINT fk6i2kjuplgrh2mlrt79nb8yg0s FOREIGN KEY (user_id) REFERENCES public.users(id);
 P   ALTER TABLE ONLY public.doc_report DROP CONSTRAINT fk6i2kjuplgrh2mlrt79nb8yg0s;
       public          postgres    false    247    228    4833            �           2606    19667 $   backupdb fk78yur08i0ognkdchy7fi2emjx    FK CONSTRAINT     �   ALTER TABLE ONLY public.backupdb
    ADD CONSTRAINT fk78yur08i0ognkdchy7fi2emjx FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.backupdb DROP CONSTRAINT fk78yur08i0ognkdchy7fi2emjx;
       public          postgres    false    220    4833    247            �           2606    19672 8   activity_log_department_list fk7lpcggd1oxuawxlo1470li4f0    FK CONSTRAINT     �   ALTER TABLE ONLY public.activity_log_department_list
    ADD CONSTRAINT fk7lpcggd1oxuawxlo1470li4f0 FOREIGN KEY (activity_log_id) REFERENCES public.activity_log(id);
 b   ALTER TABLE ONLY public.activity_log_department_list DROP CONSTRAINT fk7lpcggd1oxuawxlo1470li4f0;
       public          postgres    false    215    4801    216            �           2606    19677 $   send_doc fkbdv3116mkrd786kmf6m4y3kct    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fkbdv3116mkrd786kmf6m4y3kct FOREIGN KEY (parent_send_doc_id) REFERENCES public.send_doc(send_doc_id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fkbdv3116mkrd786kmf6m4y3kct;
       public          postgres    false    241    241    4825            �           2606    19682 &   department fkbow3bfgirk8sm778ldfmtkhnj    FK CONSTRAINT     �   ALTER TABLE ONLY public.department
    ADD CONSTRAINT fkbow3bfgirk8sm778ldfmtkhnj FOREIGN KEY (parent_dep_id) REFERENCES public.department(dep_id);
 P   ALTER TABLE ONLY public.department DROP CONSTRAINT fkbow3bfgirk8sm778ldfmtkhnj;
       public          postgres    false    224    4809    224            �           2606    19687 #   comment fkcc7apjbw63fdti2fh7jjlo5n6    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkcc7apjbw63fdti2fh7jjlo5n6 FOREIGN KEY (receiver_department) REFERENCES public.department(dep_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkcc7apjbw63fdti2fh7jjlo5n6;
       public          postgres    false    224    4809    222            �           2606    19692 $   document fkcgv0id91kwa4apcarqcfen6ea    FK CONSTRAINT     �   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkcgv0id91kwa4apcarqcfen6ea FOREIGN KEY (ref_id) REFERENCES public.doc_reference(id);
 N   ALTER TABLE ONLY public.document DROP CONSTRAINT fkcgv0id91kwa4apcarqcfen6ea;
       public          postgres    false    230    4811    226            �           2606    19697 '   linking_doc fkg1awodmhfoqy66jrkdt78rv1h    FK CONSTRAINT     �   ALTER TABLE ONLY public.linking_doc
    ADD CONSTRAINT fkg1awodmhfoqy66jrkdt78rv1h FOREIGN KEY (user_id) REFERENCES public.users(id);
 Q   ALTER TABLE ONLY public.linking_doc DROP CONSTRAINT fkg1awodmhfoqy66jrkdt78rv1h;
       public          postgres    false    232    247    4833            �           2606    19702 ,   role_permissions fkh0v7u4w7mttcu81o8wegayr8e    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT fkh0v7u4w7mttcu81o8wegayr8e FOREIGN KEY (permission_id) REFERENCES public.permission(id);
 V   ALTER TABLE ONLY public.role_permissions DROP CONSTRAINT fkh0v7u4w7mttcu81o8wegayr8e;
       public          postgres    false    238    4821    236            �           2606    19707 *   appendant_docs fkhbd56knga2fv9d2kbmsa9o8sr    FK CONSTRAINT     �   ALTER TABLE ONLY public.appendant_docs
    ADD CONSTRAINT fkhbd56knga2fv9d2kbmsa9o8sr FOREIGN KEY (doc_id) REFERENCES public.document(doc_id);
 T   ALTER TABLE ONLY public.appendant_docs DROP CONSTRAINT fkhbd56knga2fv9d2kbmsa9o8sr;
       public          postgres    false    230    218    4815            �           2606    19712 #   comment fkhvh0e2ybgg16bpu229a5teje7    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkhvh0e2ybgg16bpu229a5teje7 FOREIGN KEY (parent_comment_id) REFERENCES public.comment(comment_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkhvh0e2ybgg16bpu229a5teje7;
       public          postgres    false    222    4807    222            �           2606    19717 !   token fkj8rfw4x0wjjyibfqq566j4qng    FK CONSTRAINT     �   ALTER TABLE ONLY public.token
    ADD CONSTRAINT fkj8rfw4x0wjjyibfqq566j4qng FOREIGN KEY (user_id) REFERENCES public.users(id);
 K   ALTER TABLE ONLY public.token DROP CONSTRAINT fkj8rfw4x0wjjyibfqq566j4qng;
       public          postgres    false    247    243    4833            �           2606    19722 $   document fkm19xjdnh3l6aueyrpm1705t52    FK CONSTRAINT     �   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkm19xjdnh3l6aueyrpm1705t52 FOREIGN KEY (user_id) REFERENCES public.users(id);
 N   ALTER TABLE ONLY public.document DROP CONSTRAINT fkm19xjdnh3l6aueyrpm1705t52;
       public          postgres    false    230    247    4833            �           2606    19727 ,   role_permissions fkn5fotdgk8d1xvo8nav9uv3muc    FK CONSTRAINT     �   ALTER TABLE ONLY public.role_permissions
    ADD CONSTRAINT fkn5fotdgk8d1xvo8nav9uv3muc FOREIGN KEY (role_id) REFERENCES public.roles(id);
 V   ALTER TABLE ONLY public.role_permissions DROP CONSTRAINT fkn5fotdgk8d1xvo8nav9uv3muc;
       public          postgres    false    239    4823    238            �           2606    19732 $   document fkn8vwrnrx2lmvdmrgxr875w7wy    FK CONSTRAINT     �   ALTER TABLE ONLY public.document
    ADD CONSTRAINT fkn8vwrnrx2lmvdmrgxr875w7wy FOREIGN KEY (department) REFERENCES public.department(dep_id);
 N   ALTER TABLE ONLY public.document DROP CONSTRAINT fkn8vwrnrx2lmvdmrgxr875w7wy;
       public          postgres    false    230    224    4809            �           2606    19737 +   user-department fko7uo55j3hqk5rqvxmdnrw0scr    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-department"
    ADD CONSTRAINT fko7uo55j3hqk5rqvxmdnrw0scr FOREIGN KEY ("user-id") REFERENCES public.users(id);
 W   ALTER TABLE ONLY public."user-department" DROP CONSTRAINT fko7uo55j3hqk5rqvxmdnrw0scr;
       public          postgres    false    247    245    4833            �           2606    19742 8   activity_log_department_list fkodqlaujwsh5ww62509l2p30k8    FK CONSTRAINT     �   ALTER TABLE ONLY public.activity_log_department_list
    ADD CONSTRAINT fkodqlaujwsh5ww62509l2p30k8 FOREIGN KEY (department_list_dep_id) REFERENCES public.department(dep_id);
 b   ALTER TABLE ONLY public.activity_log_department_list DROP CONSTRAINT fkodqlaujwsh5ww62509l2p30k8;
       public          postgres    false    224    4809    216            �           2606    19747 #   comment fkooerxu4oy4q0s0duwk3vtk74q    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkooerxu4oy4q0s0duwk3vtk74q FOREIGN KEY (document_id) REFERENCES public.document(doc_id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkooerxu4oy4q0s0duwk3vtk74q;
       public          postgres    false    230    4815    222            �           2606    19752 #   send_doc fkpv5luxu01pay4dv1eoly5rjp    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fkpv5luxu01pay4dv1eoly5rjp FOREIGN KEY (doc_id) REFERENCES public.document(doc_id);
 M   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fkpv5luxu01pay4dv1eoly5rjp;
       public          postgres    false    4815    241    230            �           2606    19757 #   comment fkqm52p1v3o13hy268he0wcngr5    FK CONSTRAINT     �   ALTER TABLE ONLY public.comment
    ADD CONSTRAINT fkqm52p1v3o13hy268he0wcngr5 FOREIGN KEY (user_id) REFERENCES public.users(id);
 M   ALTER TABLE ONLY public.comment DROP CONSTRAINT fkqm52p1v3o13hy268he0wcngr5;
       public          postgres    false    222    4833    247            �           2606    19762 &   user-roles fkrbta1hppxxbup8ddfccar7622    FK CONSTRAINT     �   ALTER TABLE ONLY public."user-roles"
    ADD CONSTRAINT fkrbta1hppxxbup8ddfccar7622 FOREIGN KEY ("role-id") REFERENCES public.roles(id);
 R   ALTER TABLE ONLY public."user-roles" DROP CONSTRAINT fkrbta1hppxxbup8ddfccar7622;
       public          postgres    false    4823    246    239            �           2606    19767 &   doc_report fksj0dswnyaxui1h59u626lblyk    FK CONSTRAINT     �   ALTER TABLE ONLY public.doc_report
    ADD CONSTRAINT fksj0dswnyaxui1h59u626lblyk FOREIGN KEY (send_doc_send_doc_id) REFERENCES public.send_doc(send_doc_id);
 P   ALTER TABLE ONLY public.doc_report DROP CONSTRAINT fksj0dswnyaxui1h59u626lblyk;
       public          postgres    false    241    228    4825            �           2606    19772 $   send_doc fkssedv8p9lo5j02khcbc57aopc    FK CONSTRAINT     �   ALTER TABLE ONLY public.send_doc
    ADD CONSTRAINT fkssedv8p9lo5j02khcbc57aopc FOREIGN KEY (sender_department_dep_id) REFERENCES public.department(dep_id);
 N   ALTER TABLE ONLY public.send_doc DROP CONSTRAINT fkssedv8p9lo5j02khcbc57aopc;
       public          postgres    false    241    4809    224            �      x��][��u~^���>S�����a&��	�y�f�m��,w;K]`����?�����8Ћ��grNUuOuO�l�V�2�=��ӧ��:�:��~srov��p^�Mn>�O�N�ݫ'���dr~���?��������=�'�P�0��`.��RUJX���{����_�����������Q}��A=ٟ�/]<��ˋ瓋��~�x�x�}��>{0��g'���Y}D�&��'K���'nߥ���K��>��7����%�����C|�n��}��>>�}V��>������*��a}T����*�$���!zzv��]}��������|������������!��^}�������11�О�9�(� z�Yh��h6?O������'�{d�"��'b������y]�~P?��ٛ��F{_��~�@㻳���y�|����w�3�}�>=8;��u6�=�c�c�����\����������q�?8=��,e���<��g����7�V��q,Gߎ��Oq���uO����� �&\wM�����*[)�����`�n��<��!�q}	�_E�q�=��7��i�h�����_�}��f�����a��K���H����R�c�n�D�2�ű�t��!��Ж�\+i�:~�&�w���K�޿x�c��������ɔ��zH�.t��~s���?�x��7nဳ �݌ׯ<'���7B�^3�kfoN��_�q߿�9�d��p�<��=�+c=��ֳR[��&$T�I��;�J��d�p*����-�T#�˱5�x�l|��<Ǒ��_~�o�\<m����(�T�
�qvs��_��Մx^���
�pc\h�g������/׮��nw>Ɓyn6�MS�H|h���P�����d���CQ��LeE��!���d�_�:Y<ښ)�3�����pm�cI.���!�3/�9]���}q�y�e��(�փ�_n��e�%⋫+ɯ���������0�52l�<؀GW���b�\U\Y�U��P��lõ��\FI%��X+J�)�B\q������p6�ҩ�¢��d��j�yJ�ob#P���,B��j��ׅ��B!=�h�B%9�<��"���������/'ު�mz�êwW��v� x#��|�'tǧ�϶�@���8��9�Lc�U��3R�����q��
��N���[�Ǜu�¿b%#�좒Q�&����B��g:��˺������-�\��f�W��JY)P���H�h�N9�,Sք��F����T4��0���"�ލ�|v��$H�|���~�6J�6J�Fi�.:ֆ#���8���岹<�Ľtz�Qq� �-ų��?X�q��/��#��eR�j�MI�!�{GV6U {�pe�.��a���F�4=���L�<ӿ�"��Z7�m�!?"x���y���/=���tSv�)`��RY�0��i#E[2��it0�NCC��ȥ!X�.Ҹ�g}��Tit-�u-D�ޟ�,ŔS|\�m�1��@YN� ����E�w�#��Ueq�x�إ&��3������އ�3f�ƿb�V�P$��I\ �{���H��x 1"e�܄�\��r���:������R�b+OSM��ΘI�bP�*�` ��U��8d} F���]J�N� /P�
�:���uy��)�n�p8{�"�qx�S�'+-����9�!�[�Y�*�S�N�r>��+�������_�Y�ħ��œ%�X�B�!�蝏}��q��r��p@bEE p>;��.΃��]Obe��c�8!WI G���h㨶�{�����y����8�_�_憡\Ÿ�6Iz�1ԇ���t��/v�ɥ@�r���yɣ��/�%{�4)�\(o\�b��
"k�p����)��h*�/��aI���W��1{.Fl�&_J���榧�rL�$S�[A�2�p>{pzT絅%���,z��*kE�ސ�����y<%���j�$P2�
�Z�K$�k�ޭt��<�FZ�B&־��T7H�2 ��< ������:at�̊ey n�_vZj�G�!�=eԖm^+��Ζ.��lIB���E�M�������X�C�1}��C_N���By�5s��)�}�����ZW�ƅV �C�X_��^}�V�����nUŔ�0B�~���!��0�G�)�@\M�����y���Lڱ�$�|0;�G�QAaY^oԇ�׃r���$j�D0�����:���,�Q�Lzꆓ�JA#��3Iq��x-ͼn�X�p��Qi�����8: ~�P�[��~��������ܬ�w�f��/i2�Ǿ�a��0�ţ�c
q>�UFc���8X6���7x�g4�X-�����'_��(<�_>C���/����[.>��?'�P���8�� B�&_SԩJ�]�Ǒ���������{�QN(����s����}{�l��p�:_��l��JM��E������{w:?�������d�IQ�Z�	i�1�연!zl�'�������(��;�QY0��S\�АE��)'�'k�(dE*gC��WTy��׽J�X��\ ��>�AA� s�ziK�
�O��ChQk���v��@8@Ie����P��!
����]�	�n�X��k�<^;M��ey�ٞiv�]C��}��T���$O��x�T�h���S�R ���ڭr,9`�l�91Nl`e�84J*ay��2�)��/?�d3�z��ʄgKVN���q�Zn��� C��gk�����|qm&��V�d�Ѱ�f5q��%�Dʺ�I�"#O�!ߖ8e�*�9!W&�=Y�\S���!5�esK�
	*�$��#HYP
�d���,R��FI�PWKc�$r�Q_�>gjI�G#�+@W��=Y�w[I-D�~hkewʍ�oH7��O����7n��׷V�~�++��˔�PL�!X����������O��?#8"ٵ��|��'���{5�#"��$nL������WA:7� dUe%*���L�5���P	�>����|��v�S�d�����P��W�a�	ɴ��>���rYi)t�}�tf�^x\���e�$saQ��>�d�+�G����8��AC�c�ҵ�|�F���[�2�׷N�����zˡ�j�˝�b��J�Lt'X����R�F�����Zꆶ?J�4��[X��ҵS�6�¥����;e���YC�o��DWxW�Z����6�O(����^��B��������V�&U.��a{;�e��'�!݋��	sǴ�a�v`6V��8J�����c2W����X�
�R&���Y���y �̓�Փ}�K��y\�[���X��ʡ)�B�`��FwTf�U���\���r�I��V�t���Eq1i��(Dá�J� 7DY�Ó�o(�Q�].�p�w.|���>��I�h�u�@!�#��ٓ���}�6��cz�!��Q�h`,�F�Y��2?���t�䱻,��1it�Es��������JiC|���@��;l,��h�ia��b{ҒL�ΰ�̅�[�u'�ձy�C�#��w���;D����o�9��>8R ��\�J�(��;4U�J{��`��
>A&�R���w�X�c�E�:�r��]�Xc�Ņ�^sm�S��d��>j�E�����{��m���ԷA�B��.�� J%1�>��ɳ�Eb�(��H��<��v@RA�l �H��m�r�Ҡ,R4[~Y�&�JroG	#D0#u 7�)%b�.�m�V���o��E��x���]0�z�(ɤ��m��m=����(k���*���m.GqekA�9���Mj�ͅk�����9�)�`�^�f{�g�p���OPn�7*E�-8�;U�D�jn�PA%��;��m��U��f-�<3��^�4�h��t�jX�[�|ggB]�Jx�/��ҸJ�k|/B�c�F�[��vq\eq���2{^�T�,G-E���L����hN.RGZ+�B�T^�5�!�8�Zs!ࢊ�s���rd�L[I�&��. 7�T�� �  ��x�F(C��~���:_�j��q��]Q�}V�W�9��/4<�KmXqE�������;�@T�3�Zɥ���w�����q�T���
���T��]��3��ig��0{[ۿ��`�X���#�Dꆶ1�Dh���H���bD&���/s�L6�)�_�ݕb�m3����.)���՝X\o�̏a�\;���\E��ee�ڙ*�w�q�g�ь)�)2[4�z��?d8L"�@'=ML�;0�����c&���a�h���1[n�p\ ���2�fI�Lv���GI�Qr�aT��=._7J��v���b��<��v��5*���c&5�����A��+�Q�������-Nz����y8^.��Q�S>`v���t�~8U��L�<JA�!�{h�� �o+8(���K������A���V1�R�}����[�V;��v��.[�ɾ����MmKY�0;djwfK�)r8$y��q!;��v7��$�9��M�D�E8�;dR�	��$o2;0����b�uO/���k���N.~DdV��<dE��� 3"��緞��Ckj�Wd��h�pZ��t.����V �\�Bi��)���Ű����g
b���皅�J���-A��6k27~�}��$n�ǩ`�&�k˖ .��D��٤�T]����F��#�� �mo?�J�'�O����]��W�?A�N1*:e��f<�~箬����)����¾E]I�Q�%0���b] ����+ۜ��U!�l����M�d�1��2^��2�pe��>hNvs�$��*Tf�,j��yW8�C[��*���>��}$h�ڂ����B��\<b����t!�\�|+[o�.P2:V�1�`e��V��H.�J���[C�N����6ϭl��t55Fw-�$
��4t)_m+:l��=y���͝��	2�-]n�N�A��J�B�˦�Gt�D%�؆-�B�-�@���:��JJ�J.�AŦ`IL�O���kZ4�Mfu�-
��dC�2�q[ږPU�6U�q3��
_����'��w��G~X��ѿ���2C�p�!u ��J��9��c^������j�e/��R���<J+�J۩�\7�7�1�xD��ʦx�n�3~@���c�.��+�����O��<����Q;�9��G1g���u��t�y؁����P+u&��8F��'X�<V�弄������'8�<NN�R����T��OprY��7cZ�%��N�����`��=��#�`GL�2�	4+~C�Fk�z�]1���C'`�cmu�I���x�}�T�,6� K-�U�xX�>$o�X���#�qgv��'P�`��c���gKf&P�-`Ce�ճ#��)����ktڮ�ww1�{�@���{s�l/���{Upj�֓}u{Ro4�P i9]���!X���\�.Ӣ��y]�#QEFP)��[��h���'���xΗ(�r���7���Ʉ��Bg]G�6��&�!s���,�ƴG�����W��[:ރ�8N�F�����(�����TQI�P�������ڕa�`���3�8��4��ǁ:#cB�����!��OZSG��Ō��5�K���Ci��t���F����
�Dʟi߰�@�eF��.�r6v�(e�o�2�T��5����a�i}�.��dn�D��s��H�FH��My���3�_:�[G�i�������V�U>P��)Mm����9��!5�nI�W<'K5�iT����'ȁ)���"ܛ0e�	�\��.#
hX�!��QM=�ڢ���ϗFοԕ%�an��RVT�Se�
�7�1V�臹Nj�ҥ�6�,�SHS����r��KeB	��Y��2T*
Q�8�]\��4��x����[s�{��b��LY$,N@֥��oOh��t��R�X�F��d���k׮�?a��      �   Z  x��˵�0D�R0s�>�2��1U����	�kw(�l��EXݼE���A�7V_7�p����}�՝vN;��`��zD�X}?D�}�v�X-���^}0~�/hN�7??�Ƥ�7�1i<y�I�I�E�eo�n�7��-�-�m�m{�n;��^�@���aOx΍�f��7���IZ&-�,��LT^CQx�W�j�1�-<������yTs���Ou��s�eC=E�I��ڛ�Sg	�%t����@(ߋ�Eu�b�S\���"��G�zT�(Va���͸8����-1E��ĢĢĢĢĢĢ�j,T��̼8����-1E	������������S�Km]*#n�!*��!�՚�}�u�o�E��V�k5j�9��$4���-��
ni�HgK������9P���ږ����A�y$~��G� S>���>Ȕ5m7u����t�Yk�Z{�ڳ֞����g�=k�Yk��Z��F?�?x>������v�@׻��>��9+>B��(u+rh=���JL�tuC\H���]H�QL�Z$QL����k^I�5�$��^�t���M-ן��7��8�f������}q��z�� �[�      �   �   x����J1��s�^=L;�6�<�Wa�d��]�_ߵ� �{�Ç/?���c>-e:�q�kBЊ�Y�� VD�S�)��Ģ��J��������l,ȏ�����|~].����qT�,-+x��E�z3�n�$�H���8�h"tu������s+N��f����S��_�S��ť�$�+�$��ކ���}��O�g      �   z   x�m�;�@E�:��l ��c�Zh�
b�D�n{te�����c���
�tsz=b�o����3��Hӝ3��\a
�;��_O+��b�d��jX�t��B����
�Zӝ���kW2u      �   �   x�E��	�0��s2Eh���/Mrs'�^UP�,*8��Ap��.cj�����H��+C������q�Ŭ��r�S��f+��8�iDEF�"�X�	lE��G��?D_��!�?ML�91�ߗ�Tõ?�@6��5ʄ|��`�E~:�[zD�X��Z-�� 6�8�      �   C  x��S]R�@~ޜ�8j�����3�S�����e*L���M�r�X����>�|������<�Б4(�8�B�q4�j<?B��8�3~���2χ����i��(iN���7�&�D��AQN���u�yT�ƞ��\P\*҆�_J�2͇��i�E*�������&jF^g�K�����o{���7�BXY�8��%��,�lBx?�f��l�T+@k��fE�}%-?�E �nH�W�o#�a����ͽ.0�*,�(xm��AM�S���n8���|
��#4
�����O�vKr+�
'f�{K�$�:1�)z�;�v�Npxҿ2B| ?�mt�      �      x������ � �      �   �   x���A
�@���)��ΛqE�.�	�F[:P�:�K��t�Mc$�6�y���=>N�5A����AS �2��"�A�]i�t,Q�X9�0	Kt[��mPm�᭨Jj7�R���$0�������d6_C�f��?������k"O��:ަ�t��c�O�7)x�������,�f�zU,��х�y��8^�      �   �  x����oE���_�$8L23o~JU�P�(8�I8U�fvg@*J�6��"J9�/���������c��3�Y�ś�8�W�xv���~��y�:�rI(#�v�u;����,���H��i�Fv�;�\����o���c��맓=#X	,Z2"<ė�����I��4h�V(T�:Ov�~����T'���>q��}�w��G�G��fC��4�-&׳��o�[����.�p��1f	�d�D�aT+"JĆ�$�`Ny�u�z1��P�gC��Ùz\Z?�#�sr']�_8`�EzwuV:(_�p����CP�����G��~�c�Y��H%�T�׾�� �Am� *���V�ͅ?��^�m��|�f�Z,�L�-���nU��U�?yyZC����6�~���\�T�D�h�>B$V2It�����N�5�J�,���\���mc����	=��9��ɤ����������e�d��M�-ӟiT��ǏA:��W�c�N��^�<��ZR9���PM��[�h�������k�%Wp͵1������ѧ���w�R���
����fqnV���~���tOoeC>R}�ʑVÙ�yRj�5&N5u)���p3��,\!mahg�LM^�B�����e0���~�eҰ����2hAS�"M�J�;^� BȻuI��%�\��*��2۳�2�~c�n(�B�69N��L�n~��&ٴe��C�iިS��p�����5^��1��Iǐ �_o�j�WBJ4��)L����t�4*�D���ZY`t��q��_�2�&� fI�A��M��4ȷ���+9�c�a_G��6��1f�V�NT/�"2pL?�r�U�x ����m@.�Qzm�J�u������3^�H\�
���(�w�i�|��~M�7��@ћ����z�h$�F|�Zj+J=L]kI�^��+[��������ֿ!�7�      �      x������ � �      �   b  x���Ao�6 ��+�c���#E�6l�^��
F��b;���0���z���Y�HR����g�h[�E)m�`v�(�����h����O��hډ:�����2�%� �HI����^g����˓I|L����7��8�~��g���x8t���<����]d�/�-�x2��.�Mz�#�cF�5]Q�,�I.��6=Mߤ���|ٞ���~������)��#��ӝ�[��r�
��DR1Vsl}C��z���BDJG���m���/���(^M<����^y/��k�����4��.�]��鞂wE��D�0�����#ԑL+�V��WYݸ�fy�\��G�2JU�TՔ��w����������@�T��e �*r~����Up4�Lkbf�%C�2����Z���̄
������j�
�**��"�d0<:�k��L��t�2�.�+�^l�2�X(c� 0)�$�\a�Ң}�2Ju�\��&���L�.�cŖB� r�m�4���U�� )�q�.,BC�&�����o�{0����uI�호��f��ʬ%_<'1���ɵ����!�&^�m��z�v7*�N��׽w�|$L=K�Qڒ��\��jG��-p2Д��9жE���tN���'���bė9`/o�uuƪ�9�j�������`�2"� w#�n�^�6��t0Q��5��#X�zګ�sW]_.5�,� O!`�9�O7J��M�Q�.��)��:B��Z�[��r��F�*��_��o���|l�Ye��B�l�S���	�j��.�J[-���6������æL��sr%�o��E�RW��cCX~}��Uw�l����m���WJ��PX,� ���Zkl��6��Q�ȅ��TlP���?Q��Gc�PS-�WT�_%6�E��*o�����o�����WC�>��v�r�,��K��6�D��M����Vf�t"a�JC����]�؆�j��*�7*�jTl�e|2=�,�KeC�E4,�,�a�F��X�W�K���+ow����缻+!0�!DQ,���j����@�b�$�W�?UT�B��j��jjo����Po�����B+[��/X�����      �   R  x��W�n7>�>�����ߵvR����R@�%FYD�
��:=��%��-@��HQm��l��<)�Lg�\��#�XK.���3�����ㅸ1g���S���b�J9{���f7¨�Cr�ĥ<_����ԯ{�P�u��|�	S���3L2�/�[
�%,��Ƃ��y�^�!D��`	G�~%�,Gj9��{9�_s&��H̲K��A+N�x?�:�ѹ���ݫoZ�+~���f7-G���W�ly�?bQ�j�Yo��^V?�'���9��Sx��㜗Q�{1��ɝs}�c}���=)�b�<��	{&����S� JyJ���n^c9[Ie�R}�`Qk���ݧ�����r��e7b/�$��[�����<pHk��X�%��ި�<e=:�	dt #1hh��p�Z=���j�䉸�ӂ�_D]�u��D��4sn-p}!��͗����H����|�_����ku:�N���b���C梹�iXe�ٴ�lX;�E�	!���G�T���/�uh�9������b�r"U�aM+�b��T%J��Zb]-j۞�8��.!�S��>^�� �^�=��l���ٷb	E�n��b���`�B̽��V��٠�$�Q���Y���
���hV�/��n�\�*}�\��%�%i+&u�eM����â9��_ߨ�/�tդ�<=�����vp���c�1xF��{���w��ZHK��ί�s�VȰ}`�L���w��0��*�B`��e�Q*ܹ{��XYجvg_P�;��y�%F�y�W]7�����?i��~q�>����i���Z�N ��ĘD]�1�8��X��u��c�JE9q�T9)\̭uH�{qGӋ<�8�����0M9g��C�d;�h��~v��ϔZ�FYX.���3��J��~�6��
� ����Ft*4/�Y4c�V�Mh�(�cL���c���j.>�4n̰�Q3����Y��\�;TF�ڦ�en�O�����;F�Q�lI2�2�,��toZ1�@M��.��`��M>���Lv�8L�i�M��'���Wp`|�~�UTq)���x����@;}���l��L�`���
 ��`M��1]>��z��Z>7E���d �k�e�=�N��gפ�F^���b��	JJ\3��kka����0��7�ˎ2r���@�C����B9�p =�����o�p��kb��[B��8���` ޤN?�(�;�u�6j�>�J�Ke4����0��Q�f����db/�͂@���dIP�e^[�c���If��sE!�.mc��b�p���<R7ʛ�5�k�֩ޯLW=�jf��	$SF��b��?�������      �   �   x���!�PLtk���%�בK�!�<v�`�29T3�n��8FH�ȱf9�$�F���8����4�FOA#��%^�$J1�P�W��'>!/"/"/�Qw�����`
U�BVO@�(�|=/�~�9?��[�      �   q   x�3�,��,V(��IU �E�)�i�y�)��̒�Ĝ��Ԣ�������b� �xG_O?.#Λ�7��Q��f ��ƪ���ĸ�9o�����ƛ�7�n�G�p��qqq G<Cn      �   #  x����j1���S��jF�#B�CY;��%���zNi���ljJ\ǡ�>��~�H^�6�\�"�]����~3#`'e���h�?�a��j1��c�<N�qg�엧���8f��D�J��!(%��ޱaQ,��١�P�!xyRÎz���U��8y�� Ʀ㏊�U�<\��y���"6ObI����6i���Ř'ջ4��	����oҤJ ;��!�t�yRm��l� m@)��J�_F�m>$�������o�f1^�����2
�� � H�A�A*AVy�� ��㍱Hf+B�I�L�[m�f�8��J��x���q郢��BI���2#u��hh�����ޘA,N�m�����>N�����}p��lo��^�{1�ggg���>z�����qĠU�d	mô>h�,Ջi�3ڐ���Io�a�&!-(pM���(���<'�x��?g2��a���b�X�FT*��S��5�m��D�Z+M�)�SƤ{�VT�+
��Jc H��YڶEolQ�H*ؾ����r�\Q5���N�� �V      �      x���َ�ڲ���z/�7�t�`t$�ƴ��y�d�=);�S[U��bf�f}9*�͈����_�^�=�O�D�A �D$�3~������&�����ȸ��cIɁ��,L vx��2�᝷�Ǡ�����&J������`T��D|F�$}�� !
�²��u�Lz��<͕�}L��2���X�޷6W���V9d��9J�����b�zy�>�uVv�6���FY�����þE����=<�h�RL)�v������dY��%Xٻ�ɹ�1�lƤ��SV�V��{6�/�s�s ��e����vJ?���6CRv/"q(��w�T�*�;��_�
�]�D�9wb��[��T}c7@��!y<��z{P��%�昕�s�Кn>!k��=�$�G������A`��$և������Gg�O]�h�.zQU����L���)��4I�o�{��?F�&4��f?��*�аJw��3���d,�4�"]Ib��m{!��kA�8xpxHҹ��jl@҅�jH;�E3�P�:��+�<
��}��p����o������?��� ����p�2ve3��ȍ֞�V!��>o%)zE�6�'�����t�F7��(at���8vB��e;��_�Q��\[h��P�����O�T��Q(}178Y"쫹J��b��>�NW�m�F�`��[��+��. ���&x�<��g�����[E�7ZN{�d�}�b��fP�3�90��#6�:2?«�����fE �KM�m�(;0Č�
)&�XWB6.vl�ΪA����?��߁���ߥ2�~�Jق9J40�goBJnh
s���%Mv26��W��م�@�\�����l�ץa�QDG��7ui��
v����(��Ϊu��=��e�|�6�:Q�?��O��d���;����c������,G���>"��,0�j\�]8�?W�߅?=�o�c��d2R,KL{�����c��@�8�c957�W��u<���O6?H#<Z'MQ,1n��u7� ��� `%���up���4�u<�/��:?Ã��C\�?wLu��I]\@�Zrھ2�ԓ�;���[F����s��xTN���l2TJ��l��Ə[�(0�W� =�!ǎ�'�t�����uX¯��_�������d6<�_(ܢe�t��f��ǀ@*�x�T�]�<�|�3<7��E1N5¦��?��y��t϶A��{qa���3���*N*jǸ��_�G��a:�#^wyq�?z�a��7���(G�1QV^tu酇�WQ��u�x���k���rժ��H��*D2�g�BG�W�ڂ�dG|�U�U��>~���x>R��h�j��N}<j��10uٲ-�(�rC.p�{a���Z�cd�!�}h���H�c��H&�<D�Q~b�*�TݳS�#��Y��N@<ȣ J��u�P��?�C�֔^����gX@��)�
5y	2g��}鸄H����R�;�S��������Rs8UKطMTq�'��avI��
�x�N�.��Z�H-��u�f�D��({���L!%Y��t��k��k#�v�\��y��qCQ 9Z��s�S&H�O��:�Ų�����2}ס��h�>�uwOmUG�˪o����n��s�_!�G;����x�-�o�P᩵U���S��1\�s�p�N||.�a��3�߀�ur*I���:�|��ػ��u��]��!{�1����B������~a6�*��ur�	?~A���kl��FV�8�_��G�rc���%� ނQ�I�W�d"3<"���Q+�*�7��{
eXy{Թ�Ʃ~J�����C��s�HrbX¯b�����m�R4���h䗝�R��t���8��(�P84+���_�G}ÿ�=����X.F���\�^���7}��A1JM"D�-pyaW��'��#����L�J����4��߼햨QY����,�٠}%��s[De�j���V.�����3� �	h��������*��չ�����K������{�8}�ssũt��Ql�x_nd.f�;�+�:�E��1C0��-�W��l��l^��杘J�ɱ���H�Y�'����t�\��	�i��-�2d��atH��LuohR����On�1���h�����l�*�?u����%_��R:�����/xu~��Kh�]s�r�Zj`F6�#{	Ὤ���d�zc��4��K��T���e����?c-���;�]E���]��s2JR��+�-����O��%�#<�^�)<@T�!�c�yl/@�� ��!?�t ��]� ��?��JY�"��d6����!����F	AHYP����i��MĻ��A�v1}�S�dY�I��j+��2K�}=?�#�;��'h{cZR/���psm�e�h����0%$�G�F�Ƅ����F���<X�Я.d���⤐��$t1�/�3�e��ju��I�u�Q燩�aj�V���G|�%D,�fu�r��iZ��7C]��*O����g�f4������S�D��:(y�=E���}�D�:{mCr6ҞYi�b��O�9�¾�4��x�ӛ`���6�p�m�]ksE#qt��e$�j����G��\4�`��0�NJ�%��&xy�֞&K���H����0N�%���en�0a��y�;��,��{x�NR�N�ߥ�6�^�Bp��<��m���E0w��Dy*��_'��A>3c�Ou̺�5��=��a��K�]QB��/�5ɭD����[�����j�����{�$�2�P���Pv`LᬥCAxN�@��co6�ׅ��Bm։�;��;y��ay����E3
�m�Z�*��ẋ����<���dPG\]�6��saGp
�L�G�9t����&N!Ƌ0
��88j��8���`��M�[�E�����/���"#|*��"�F`(���d�y�u�{�����K���iV�?�~���O��63<4�m�C�w����ŷ�f';��	��<-J`ڗ�E�x��E���l3�K�{�{����-l�jؾ�0�!Q�j������^���Gv�/��ϥ�7��S�V֩Y��������������$tݍ�괆�=� KNy	�R8�w���	��K�G}��-ه)K누K��l�tg�P7,=�N���WyA�����4p��4�:<;J�p"�U9�,z��]�;}��C���;����i���� ���ۨs<_�n~V�p�Y\��b�YX��������t�R&����=���Lm簬��1��N:�h� ~����{
�İ?�6�H�}؟2�� Oe_h�2�Y�lF�lT�OxE��	^J�G5�y���4;�7d�죏�OuR]�-�l�4p����;�ӅUTؚ����F��ΏC��-M��H@�-�.�J|�B�7����Oo�h�+˪wY!ÈV#�(y��i�����ց�7P�XH%�Nl���~ÏV5!�/o�S�e�����6%0�TI��6�[��l��%�:��7�g<?ÏNJ8�Jx���l.&���u���G��3��Yju/vy�P|����=����b�OZ�
ڻ��R��9��Ι�K}�Α���<�n3���Nx0�w����s�t� KR�wc4��~�Y�qc����S �& ������hs�W��	�������͡��'�a�&�DJC.Lt(�y�I��kGd4C.�u��t~�� /t�N,���LS�8�����N��x��68��$t��y|=��ߣ�o��)��z�l(�ővfg��������&����YT̈u�u �5����=��\�lk� 	��E,����~8�-DZP"�}P�,L}X���dR#�(��N𻍼�XJ`ٻ�H�}Wa�F޺���`���m����pZ\X�ϥ2w~��G���mx4�v�'r�d橂/���Mf�7`i��P��W[�2�3��qR�?��߃3&���F��p���Y�
G�� O�lR��v?��|ǘ,QQA^��<���u�~�ۄGX~    ���A�9;ֆU	J�ؤ}��s9$����`�{@�� ��ImԹbj��r�z�B��)G�{t�l$a����h-��H�+�,bl�3)i�>b�1��v:y�o\>�w`�<� Ɵ�jѨ���}>�G?	��5�B�:���4?xX��/[����AA�M��`A5��춱lYP6��9G������0�u<�h�\���6�Ssx��M�Vv��ژzc�7����!ds�f��R)���X��~��I����g]E:�&9̋޶,�(�����N�B��ex7�Djwې�dR��:?�O:����EK��w��@��D(�Kt �^U��ѫo��E7�������	~4�r6a��G�f���J8��6{_$։��y���u�yh��}��L�_�6U��̥�7���{��)r���5i=��E&}n�B���\'���3��y�tm��+:-�s�]Ѝ9�b{�t`
L)��<���6	����L����W9�G�l6�w��@�Tu7H���\_N�=x�o������� ���E�T ?�}�4�1��dς�H��MA�Fش�m�;�{�4A�Q���/�L����N�do�:�Oc�������H��&qF/��
�@�Plo���ǐ?�����hZ!WSz}���2�l���j�$ ��A�2�^��eWxU[UU;/��49u& 엣_G���3���A�9����%!����-Ϊ̉��y�����|�O�ON���`�)s�g,��1�Ծe�1}�_+�.�-|	c�U0��F9�w�|�d�訞���Z�Q�x*T֥,'C���rp�t�8���b�D*�4Lwq�k��:5��>�_G�ѹV��g�K;��t� ��$� �rwo�<�:B z�m��T���ǣ��_'�����W��j;�}��:�A�_����*Q�<�/����E�6�ʽ�~����~�o�!�)w��4ߝp����U�jb��U����_gi������/��V���M�;�s��I���0H�MFs���;Xh/�~��pt�Ƙ���(O�Hӻ �y�v��VH"G�� ���o��<[<D6�]
�d�_������j�	NPz�l�-Az��c'%O7��R�b��0����IA��r�k�����M�c�9��p�]�"�t��҃D]��O�(k�[��9K�\g�k���g|����4��W�R���6�ҹ�����+���Ȯ)�YZ�_X�񿸵?�:ws�{�r.�Z�\ 	�R��Ƹ�h$�e�kv�&g��uVgL���1�3�� ��;G���Վ7�U��M-�(g���y�y���^�x#Q��_%-���S���~ԛ��=��S��<�m�]с<�-W8�Y`�����80��0ɏ9�U��3=�>��E�ͳ���CF�.�P2�rb���Z\��;	�u0�WX����lȦ^������)MG�H�{�Y��Z�l�F$���|T���B�B��5Bd&�����W��gN>��WN���ܺ��qs�ZNcz���rv��M��qiI�JZ;}Arʽӏ_64�����E��xo*���%����7����P^B,�Wy����_�z?�Z{�mT�-�W�L��sv�l_2J����5^xj����*�I�N�㔏�k�TB�SI� j�gk�G��|݃�`�Z�`���,���@�����Λߥ>K
�:�XL{rϳ˾B�h�a��Ir{���`�����@����U��l����h�z���I1�'�˝��
�w�|`Z��3�m� 	4_��5�o�E����~� ��[�O/��tk��]Ho<{i�5&�F��ક8���{�=0�I2z�������b�������_�ѼT��A

ށ���D���ŉd�*0ok���{���Z���� ���cf�O�y��o��x<��R��#�`�s�b]�`P�7���<�Ur��~̣~8�� ����L8��ݿ�uY���J�폾���,[���\;b����=��:�9�C�3�|-�TauS�x�=S\/]����Ά�`���/����R�٧��+�q�c����L���H�$�".�QCDX�J�uxM�^��?[�~"Ճ�<{l-z���D��c��'�. !������� ���G��,{vثv3�.��Χw�e^�k�G����H�����?��U���L��6���u+
[oa%���I)+�^���׉�a�����9߲�{�&,yLw)v��]�6�<>������m.c�u�L��{�{�6�Ob*\�r��ie�)˛�__�m����/D���n�g��@.o�:�f�6�ss-�Uwb
tS�g%e��X��Y��3�R{�S$�v쥯]g��7���L���к�������;G���m/�-P	kP��9j��]�s֙;���"�a^�:�}aF'2e0�9�S��E�ENIݩ�n1�Xcv�˓��_';����ɒ�ޘ'K������!֛;�bU��1>���vib����$Z�2;�Sx���=$?Y�=�kҷC����h�*�y�Fi-���4��.&��}}�`[�v �u|�L�Y��犈fB�6>���ȵ�@ �~�Y#�1��ȍ&0Ȓ~�j2�e��mѧ��h�m�Nj;�oUӬ�-�%3�ߞ;����#ԝ�XT�=_���:�)2t�@)}��ӿ��O�VБ���+���k����Of/��TL�$#+��a��/�H��O�F��߼��4Ȭ0{��朎����9P��dn�0�;�-������2	(����Z���,7U>����H��~�Cm.��?=��u\ώR׆M%�PH2�ƕ���5��0������S?�K��9b��7�zd��1^Q��Cu��f�]@=�P�jMg����G�_��o����E�����x�\�U��{|����c.v�xʍ��_�W�_�>����E?�s'c�̉�v���~�yWk�m����K�kH��>�5���]	����7w���������|��e�j\��u%����Gӟ��!-�kט����w�1F�]>�y��it�;WT�A�Ź��M�hR�[RR���e�����;���!����"8E�|���^�cO#��Ұ��UǼ����e+��� 1\*��_�ӌ��I?��L]멸���Zչ/-�u&�w	�s����IMH%9/ �1 <ҏ~�c��� ��@�8�b���d/8�A�� ��w۩�tߢ������j�	�oz�m��7�=�Ӎ��~�*/{"nE���]�z��C&]e�x � 7�ƒ~_�_z�G����9Lo�W�l�"�
�uP��-Z�ڃj�����1=l�����e~��ߦ�E?|mS�:������ʗb_ئ��h�Z[��:�@�d'���eW������?�O�]�a&a�o�vvXn����e�,��F�s&&����2�\c1�7=���9�Ȥ����.2��חy���eV�����l뎆с�@	�~9�U|���c^"ƺ�v*�W��'t7��q���x8����{�Y�D�ab�w�!�C�'z{�h�u,]�N=w�1�*]y���X��
;�p���w�����A�����Pd �z�F!p���Qם�+�W�'cw�EU��F��B���WS�hx[K4�O��*���kB>�NÖ��l�j��
����?�n��G(R/�hI����L?���z�7y��T"^��Џ�-h1l+�T���e��i<yb��T�/��(洟p�?4g�3i~;1;�E����.n�_����p�$F���K_��<�x�蘕�o{����Xcޛ`��f�$,	D/�a��a��5�j����_*�a��7觏̊��^X@NU`^�5�*�D�\eʢ_j��[�wGp�����m�<국����D�����O��tk��W[&j/�z�H�=>��k��f)���>���"V������z_��=����I��0�=���I���Yg�Ic�I��җvO�rk���
��#�c��HI��� D�p��0�6�=U;q,�|6�u��*q���-�~��S��6���vW����s�ez�����atը�N/ �  �eO�C�G1��9ߊ9�s^h����<?����KA��G�e� �KQ��JKܲ���ʐoz@��&z��^��A�+��#��/t/y���C8�l ��+��[���2�������R?Џ��nڲ+e���?i[�+�8K�B*���C�m��>A��_v�_�~�s��F;�>���7D�Zk��\ĝR��]m�0�
���1ۓ����z�+OFzD�������mg����@3��wɇ�6w���ˈ���i���s�TEhm���U*"2z�^z���_���z(��lϨ��������X��Z	=�=�,��겎�@���M��|m?i�X����!��[,tcJ��O�vcg�ug<��NJ�aY�� ~�~P��'7lıU8�����k\������O��Q�ߨ���k�r�e5�/���m�����5�t+#�Ș��Xn�2.I�����G��:wm߂R��V䟿x���W��>���3�RS((0�C�y7g�Y��>����D����}�����݄���׎������h����<w98X�-���^���IߦB���_��7]�=����3�_�3�Mz�W>"d{�E�8�e��,�Qa����:���{�PCX��
Q����T�s'k����{�#���ޞ�Z�:Eg!�'�~�X���O#�� ���7� ��evB�y_���}��d���y�t��8�1�J��p.�ݒGo��(���獹���?�K�Mz�}��L/�:n�L��������O�i3��O�%(k9)he���/o�:�N�b��tz��Z3�m�2~�k��[���4ǚ�V@rFt�j�\v�1?���PKzbzD�&طH!�>�h���Co�|e��nݒ">�{�,Ըv�z=����� �52�n���j��'_�6딟j
:�L�!x�P�w�"h�(i'���r�0�sqN��MV�{���Z'����.�/zu�(M(�y���0v���'��6�FW��W<|�ǋ�[f'�:�J�O�y��1�I�y[���i.�6]��j�4��#����=��@`z�Es���Q�C1'�l����hI�t�-�p��7X�JU�=�9�wnb�P��]G�GO%�o�8��1���E���Ni�Bz��G�+I�EK��9��8$N�%�:g�Lۦ右Ϧm�s�=��Pj�A�2�M�c�����p��=a�3u���X[�}k���g���<�*gqT�
xjo�q ���Nmj�>�:�W>~�=P��g�������6�y��~8�Y�ܔ�5L�vRU4�P���z�B������Kz��-���s��������3P�1��"려-�N@"h �ȳ��s�f9��?UXd�_'Ι� �#���^�yΐ��]~���m ��^y���#�^���,Ӗ��A�R:j�;�@�J�Y��ļ�7V�\NpS��Pp�SNoᐸz����,A~�c���o'�[7��ۉ:w���9"r_�6q�r���`��Cy�yn�4�h����Ԑ'���y�c�}�N�!Y|�x5O{��W�9mkf�?��(�/������������3��P��EoA�Z�Y�������ϓ�w�皮+A�������!+e'�O�����=.uWV�i?�-v��5�zR�^/�pb�9���!�B�T�u,��$���n���:�2�v�_�M����x �*,*#�3hg��"�������ֳ�      �      x�3�4�2�4�2Ҧ@ڈ ?F��� ��[      �   !   x�3�4�2�4�2�&@ڔӘːH�=... ���      �   A  x����n�0�sx�z�N�8=H&�@�*�@��b��­�n=�=�M����lO��/3�V��H��X��ϿlC!r�t9O7׽�@Y�cL�(Ҭ?
�˼��!s�q�7eB~3��<77�9��v�.'QN��ʲ��I�6g�\�XKC]Un��$M[��9,	@�P!X�X-�>;�]:ՒW���Sђ)P��}�t�W����]r��B�=��|���ک� ~�Rm�:����*�̂��"����b5�bQf�����󗏅��x���[�OB��Ќ�V�XzG�j�z���fF�y�c���/���u$�j�Š�&�I�9��Y�bޥ|5_x��3A��0gTuCwE�ՍX�*L辘WN����(�����FN�����䖣��Gr�Yf_?9fΨ1���vഭxm��kL-�6e G����dx�W��J��'l�'XDPz�'7\� ��RӠ�#J!B��{;�\���P""�� ��DG��eEF�KΊNz��YXZ����׭bv�6-�37�A}��^�W�e�����"���������ɍ��N�{��d2� �UX�     