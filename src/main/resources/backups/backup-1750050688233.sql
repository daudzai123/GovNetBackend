PGDMP      )    	            }            DocumentMIS    16.8    16.8 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
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
    public          postgres    false    216   ��       �          0    19480    appendant_docs 
   TABLE DATA           �   COPY public.appendant_docs (appendant_doc_id, appendant_doc_download_url, appendant_doc_name, appendant_doc_path, doc_id) FROM stdin;
    public          postgres    false    218   �       �          0    19486    backupdb 
   TABLE DATA           H   COPY public.backupdb (id, backup_path, created_at, user_id) FROM stdin;
    public          postgres    false    220   ��       �          0    19490    comment 
   TABLE DATA           �   COPY public.comment (comment_id, comment_date_time, comment_text, status, receiver_department, document_id, parent_comment_id, send_doc_id, user_id) FROM stdin;
    public          postgres    false    222   ��       �          0    19494 
   department 
   TABLE DATA           R   COPY public.department (dep_id, dep_name, description, parent_dep_id) FROM stdin;
    public          postgres    false    224   C�       �          0    19500    doc_reference 
   TABLE DATA           H   COPY public.doc_reference (id, ref_name, description, name) FROM stdin;
    public          postgres    false    226   ��       �          0    19506 
   doc_report 
   TABLE DATA           �   COPY public.doc_report (doc_report_id, action, date, doc_path, doc_status, download_url, findings, report_title, target_organ_response, send_doc_send_doc_id, user_id) FROM stdin;
    public          postgres    false    228   ��       �          0    19513    document 
   TABLE DATA             COPY public.document (doc_id, creation_date, deadline, description, doc_name, doc_number, doc_number2, doc_path, doc_type, download_url, execution_type, initial_date, received_date, reference_type, second_date, subject, update_date, department, ref_id, user_id) FROM stdin;
    public          postgres    false    230   ��       �          0    19522    linking_doc 
   TABLE DATA           a   COPY public.linking_doc (id, create_date, first, second, department_dep_id, user_id) FROM stdin;
    public          postgres    false    232   S�       �          0    19526    notification 
   TABLE DATA           f   COPY public.notification (id, content, created_at, notification_type, read_at, recipient) FROM stdin;
    public          postgres    false    234   p�       �          0    19532 
   permission 
   TABLE DATA           T   COPY public.permission (id, dr_name, en_name, permission_name, ps_name) FROM stdin;
    public          postgres    false    236   ��       �          0    19538    role_permissions 
   TABLE DATA           B   COPY public.role_permissions (role_id, permission_id) FROM stdin;
    public          postgres    false    238   D�       �          0    19541    roles 
   TABLE DATA           ;   COPY public.roles (id, description, role_name) FROM stdin;
    public          postgres    false    239   ��       �          0    19547    send_doc 
   TABLE DATA           4  COPY public.send_doc (send_doc_id, action, ancestor, doc_status, findings, guide, secret, send_appendent_docs, send_orginal_doc, sending_date, sending_status, target_organ_response, time_to_seen, doc_id, parent_send_doc_id, receiver_department_dep_id, sender_department_dep_id, sender_userid_id) FROM stdin;
    public          postgres    false    241   a�       �          0    19558    token 
   TABLE DATA           W   COPY public.token (token_id, expired, revoked, token, token_type, user_id) FROM stdin;
    public          postgres    false    243   ��       �          0    19565    user-department 
   TABLE DATA           G   COPY public."user-department" ("user-id", "department-id") FROM stdin;
    public          postgres    false    245   �      �          0    19568 
   user-roles 
   TABLE DATA           <   COPY public."user-roles" ("user-id", "role-id") FROM stdin;
    public          postgres    false    246   �      �          0    19571    users 
   TABLE DATA           �   COPY public.users (id, activate, downloadurl, email, first_name, last_name, otp_code, otp_expiration, password, phone_no, "position", profile_path, user_type) FROM stdin;
    public          postgres    false    247         �           0    0    activity_log_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.activity_log_id_seq', 269, true);
          public          postgres    false    217            �           0    0 #   appendant_docs_appendant_doc_id_seq    SEQUENCE SET     Q   SELECT pg_catalog.setval('public.appendant_docs_appendant_doc_id_seq', 4, true);
          public          postgres    false    219            �           0    0    backupdb_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.backupdb_id_seq', 5, true);
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
          public          postgres    false    242            �           0    0    token_token_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.token_token_id_seq', 216, true);
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
       public          postgres    false    241    4809    224            �      x��]͎Gr>��1g����7bi�҆�,�&9�d�Ù��PZa��%������%ZAI^C�}�i�e��U�U]=S=Y�gn�TGe~��)�~s|~��h1;��8x8?�߿?;�Ы���ك�d���l�p/|.ܓ{ @�#�; &��JW����ޭk����ٝ���ޟ������٭;g��������819������g�����Of�����h19��y G�'+����!ܾ�7������`~V��sxH/i�?��>��D����GgGg�Og��Ύ����U�7���٬�;��7��vQ=9=�������������;�Ňǧ�O�Xgw�����~7?�8�=}��iϏ��1� �z�Y�h��p�8��/�9��?:�}tr@��Dx�����f��g��-f4��|����w������M�U�2}��������ɝ�3���Y�D�X��ߤ�Q����x2?�e7͛������Q6�����ѿg�[�.��&ܪ�s���G�����s�"��B݃��S��@��뮩Q���S�*�(�{��5{/P�Ю���+��.:A��)o�������ϗ��b��������o���X��?^~ND���9bw��OT^k�}����^S2��JT �:��7���g5Y<��o�{<���4�o��hB���8�-�ɭG���BO�7g��)���w{�M��������r*�9I
��_�{&���O�������g{�C{m���{���m�w4��E((��PF�
n�� ��T%h)���3)�#��իxB����fB��[z��yz��Y�+D�V �)�
-�����؋|*l��j�c^�Y���ƴ�NNg̏-�~�q��w��M,���	8�هq��
����	�?�CQ��C����G����zA�d�x�AawPz�3ךqI5�3���R�-q8�Z���K��g]ι9�fPV�g�ZT�	�3�%���W6�^�u�S����7���x���(v*u%��Fg����f0\k{<�e�Ґ�e��QJL��H�Z�U,W`"�T"q�ʁ#���)메j��$F��&³ZDZ�3�� A�B�I��JI�N��Y����!)��O_L�V��0�����]A��dͅA��S��3�g[a`���4�Jx�m�����"�(p&�:�v)� c�S�ta��b��XȨ�pI��ʠC�i�`鱰m_uy]6���D#[��4����L�T�J��d��f�F{�n*E�v6�o?�J�l��
��*֋{7���#��;h� �c�y4Jj��[/�[/͛�Y�H���莬/��~�����8����F�	#v������`%Ǒ���J��Rp�)(�:(��mCNCV;6#Ud}�Jm�,��n���&{�cR=���̔<۽�fh�cj��6�H���'˿��z����I���A��A���(�T=(s�H�)�r@�; C�i�����#8k6y�$EwDv������b�/PV0��_Gc[j�/RVS!*ĸ�A��h�x��=SՎ�M�Q\��?�O�=�����G�7�W���:�y�.��l/���iC7~�)��VJ�6��M����I��:�֞��j$k]�9Ű�T�,�PM�W���8d�#h�@��є�u �AV���(�䈻E+���%����A������Sh��.�y�ݺ;Tࡒ<ud��փ�S:��;���+�"k������e�E��DhTd���Q��"���.9Hʨ���g!���y�Þ�H�-|Dz��'�:s��<���a���^������l���es�t~�7�+!�qY�C�!>}m�h*cio���.E���㬴>p]�~�,�+,I����r���o$LցCq0��(#��� /�g��入���p����m)�#�c���2�a��VB����������_Z�Q���;Ġ:8�d���;XT4cOH�S}�b	��"u���
	x�"���lX[�A)"�T����ʆ!��B�P��%��'�o�T�l�<X�o�b^�;���Q��1c��@��%���3��2żܳ�V
D2�IU�9_ܹ{�rv�;��}��Z��+oqA�\�bv��n��-r�6e��y���P"��7;jM+{��k��b����?�q�|e�G���v��y$�� ��"�TV���B����G�y_�"(n�����z�Q2^�$ɔ1�� ^�4Rg���E9����l�����m�������mˆ�au�?��nh"N@�F�(�	�Ž�7�}M�3{:7g�{������哐���[�����	�8��.���lp���k��S�sʖ��p����I� }���/�r����_�-�����1�O9����#��"�'�_�ש��]�瑻�������{�qL�����7�s�7w���m_��VM��A�A��Q+��	l�����)�,?�L�=��$�k����~�0�3�$���K2u�0�=�mTA!$���0�dɨ�+�:1
Y�.B�]�<�〉@V��We��.BW��d5燐�`#0�H���� #]�[A�	�"��2�#�#$@E���9Ji����.O�:�+�q���7�H��#�Jmw����7OX����-�X�0dԮ;j?��h��Y��,[ʘ�~�x�T���{��*���x΁�+'���-OJIN�֛+c�@�C��d%X8�.�_�<�j�\�^�����K2e��o��n\�sO�B��7�|?8[�'�O����󋇄�!q�������4�b�oC��c���Ř�/�́,�|i8ĉ`L��b��P�D��y�Q��bOQA���uv��_ݗy
�ĉ<�jem�D���K�1�L�U���ws �C��2 Q����[�.�\�������7n��׷�j?���c�|�1��Ct2��j�4����C�1�?8�Um�����x1�o�� F~ D�̌��E�u�,���U��Pd��r���[݃&���\�I�l����u1�~;�)IV�H�����(�X���k.���H|�I��T�Q`R��0=5{�q�|S�=����#��uYŋ(�G��������c�C�a��u|0V�@]�qg<8>��:ޟ=:<�l���}a���;!���ڃ	U�J�DmJY��k�[.TN�˷0��릊t4M[12�-���2����^CwoL��TtW�ה��eZ���cv���լ�U���j�$e����gX���2��KY��!�f�遵�n(m\�4��v�7s)�5�)�;��?����F��Y_żβ{��0e�4�z���ׁڴ�ZO��_�Ջ��4�M�:�U���<��1)D��4ڳ�`E\Uk�տV.WI�i�ʣS���$'�F�]4+��D�e^�@T(���A�,�1C���e�M���8i˝=���F1sd9�Bz�v5�e阁l�{Kb#����+w��ǉѓ��W^`Z<n�y~3'C&1knvqq2TM�-cQ�U.�ǥ*vt�Pa�H�$�� ƥ���I�<1���na�N�kR�O�!y
�w��O"�yw���[��T�AGb�Ɛ���H�Q�С��Na�a�j�:�sj@�+� ��Z�g3LC�5`5M���6Mc8�<v�z���E�6�͹y�M������󺨱_��-��L��g�n6�:��sP�&s!O�Xʉ�	�eÖ=�-���`)'&�2��5�R#gC�K�@&4m��+KzX�HIm�eq�z�(*%�z�8���Lԑ��5$/^�n��I��_�7mO���8�컓��cG+�Tk�n�o�ٍ�A�ޔ�48�py�+;�����mB�o.\��זX����3��J������B����L�S��Ƕ��K�D]qn�E�.2r����Vo[e�mֲ�+�����Y�C�4�NsΡE�M���WvfԵ� �~����U]zj��5߲緛�*��-kݕ��ڗ��|Աg�'TӸY�f�u2���>vH���5i-�0F"D��.��I���Q��D�4X�t�� �  `��᜵����'m�4�P5Ķ��#6ޠ������W]�5G��feUZ�F�B�sa�܆�v4�`���<�\��C�� �e����������T)۽+r\Sr�36w���˺��
��ۺ�m�3Eka��ތ���[.7�Cl�!�H ��dD�'җ�9��L�+S����+�2�ftqȚ���#Vδ:`I��3��y����*��:(��ک��3g*�&�%�������l� �u#3���0A?L@Fz��bw`z��-HՏ��uh�ݢ��k�l��q�~\�Ry�%י�.C�5���Gɇ�QJ[��|�(�b��	x8,�+��9���C�\�Q�p�l?f����/[��1�B��x�~�H#�-���Bo^�<����e�l���Ը[:�Nկq;��rPvH���d8H�ڷ��� ����}8(����܀u;�k_��8b���3>�ޮ�eM;�;L���8��f�T��ji�"�CүW{	����vS�Μ��!�ש=�Z�K�C*���pH�Uf�ֵ�N���ءGݳ��;����'��ف�kkY�!V3h�ȼ����s����5�g��h�ZΥU�2���m���\ciM��S�I��k�k�B��PmVGn�r�f����HAp�hi<�Z���&z��&������ �Ag�;.@���nz�I_Z��g<a8��+���_V���R��NE8�Y���ߥ/�hj�J��{a�98��oQMW��k	m¡�R������Ր����Ԛ��1f'uJG(lZ�R��W�
�S���uВ�X)��u��(�Y����*��Ķ< �2���>S,}�� J�[Jt1vL>�*J��E�O.�5� ��[��܃��U�B���l�����ss%!UP�(�o5]>��p�QW?�����5�4�̵tt6��r��UM,�<D֦�N�{A��tK��	6h]eu��l��L�OT
�.���m�Hׇ�:�RZ)Hg%��S�t�&�K'@Aa���bS8�GC�A܁ll^�$�b'c����&+"Sn&�_ҋH����	�����oy�D}z���RI�@PWi�ޣ�7�أ7zC��C00[bՋ�ɔh�9��)��v������� <�Jഩ�����a���"�[���VW
�gx`?F����;�1����槝w��t�q؁����Tj-�#p��ϰ��Xq��Vn7�7�����q��*���w��~���ŉ{3�	Z�b7pj_>(L���A�-6�;���ϠY�"4����}:�_16��LƎ(�cG�3���b+,�\��]U��E�3@��bZ�Qz�Q�[�cH�>��_�Zz��L�{Z�`fE�l9M�%zvD�>$�aѯ�Z���+�;��`.��^d�do�.�q'�j�
N�z���'uf�-;�V�1�^k���wJ��LCy�~GB:)l�� �G�ťwi��&BRm$]�{����A�P�N t�B/ul@��7
H��Aaof���93B�
-���H"]�MB��:��������ď�.�	\ճ��PAB$?қ�W�P��<R��ds�F��&I������2��&�����:졮�:!b`Ҕ�L�IO9 G�U��e�نI8>���.2(�՝�y�ު�k1�:ii�6�R�)���e3�������:K��l�]F��˻k�Ӕ��+��܆�C7o:�L��n����_��0,����)Z�;>�#�~e� ��#�B��BhM�-�g٢�g���ˎ��x��Ӫ��!7q��6|N@��6MC�0��(wU]�pr�D�ˬ�T)B�r'lԊ{��R��B˰ ��M�-�OZb)�FK$4�;O���%"ʎۉ@8Q�NT�[�8��밖�t�B7���T���^��nQ��!5Ǝ�n�#��>(�s6Sj�Ў%���<w­��Y�Cs���+�S1����V���Azd�˔�ŝ��^��Nzqa����Ff��� �R���p�W�[i�Hy��4?|�[�V���{յk���a\`      �   a  x��M��0���a��~�K���v�|�@޼vb`�y�amt��1�X|�|K����f��Vw�9�&~��jF�mALb�a�m�1��c��c7{���A��M81�`�`�����ޠǤ��&�'�햽A�5ވ7�������@r{5qj�8t<<�97Қ���,zR'i��̣�0�2Q=x1Du�\�B���h��/j:W<�Q��NW?�Sk̙��U'��jo*�M�%t��YB��i�|/�_��C���x��=�ף�XE�
c�%P�m��!�8�%n�)J,$%%%%%%Vc�L�`��!�8�%n�)J8%�%�%�%�%�%�^j�RqsQqmaugmը֬��{|3/BD���X�Q[���&��u}@�m�lUpKD:[�$�%̉��&�Զ�w%x�2�#�S-=R��<�d��A��i���O���Z{�ڳ֞����g�=k�Yk�Z��ך�6�����b����'����wG1}�sV|��C�Q� V��z7�3��:�`�ꆸ�D1]��4>$����H��]Y׼�(�klI�5�$��ܛ��Z���/�?q �������Ex�>r~q���_��?)�c�      �   �   x����J1��s�^=L;�6�<�Wa�d��]�_ߵ� �{�Ç/?���c>-e:�q�kBЊ�Y�� VD�S�)��Ģ��J��������l,ȏ�����|~].����qT�,-+x��E�z3�n�$�H���8�h"tu������s+N��f����S��_�S��ť�$�+�$��ކ���}��O�g      �   �   x�m�;�@E�:��l ���Ϭ%PBB�I�D�n{te����{�japiij���&0|�Y�ktM��������Yկ\���f>��sXj��/fH:��а���*��䅃uwjΆ6��\eԪ	�l�sD!*íTJ�]>�      �   �   x�E��	�0��s2Eh���/Mrs'�^UP�,*8��Ap��.cj�����H��+C������q�Ŭ��r�S��f+��8�iDEF�"�X�	lE��G��?D_��!�?ML�91�ߗ�Tõ?�@6��5ʄ|��`�E~:�[zD�X��Z-�� 6�8�      �   C  x��S]R�@~ޜ�8j�����3�S�����e*L���M�r�X����>�|������<�Б4(�8�B�q4�j<?B��8�3~���2χ����i��(iN���7�&�D��AQN���u�yT�ƞ��\P\*҆�_J�2͇��i�E*�������&jF^g�K�����o{���7�BXY�8��%��,�lBx?�f��l�T+@k��fE�}%-?�E �nH�W�o#�a����ͽ.0�*,�(xm��AM�S���n8���|
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
��Jc H��YڶEolQ�H*ؾ����r�\Q5���N�� �V      �      x���َ�ڲ���z/�7�t�`t$�ƴ��y�d�=I;�S[U��bf�f}9*�͈����_�^�=�O�D�A �D$�3~������&�����ȸ��cIɁ��,L vx��2�᝷�Ǡ�����&J������`T��D|F�$}�� !
�²��u�Lz��<͕�}L��2���X�޷6W���V9d��9J�����b�zy�>�uVv�6���FY�����þE����=<�h�RL)�v������dY��%Xٻ�ɹ�1�lƤ��SV�V��{6�/�s�s ��e����vJ?���6CRv/"q(��w�T�*�;��_�
�]�D�9wb��[��T}c7@��!y<��z{P��%�昕�s�Кn>!k��=�$�G������A`��$և������Gg�O]�h�.zQU����L���)��4I�o�{��?F�&4��f?��*�аJw��3���d,�4�"]Ib��m{!��kA�8xpxHҹ��jl@҅�jH;�E3�P�:��+�<
��}��p����o������?��� ����p�2ve3��ȍ֞�V!��>o%)zE�6�'��������n:yQ��\?8"�4q�&��vZ79���0�I8P�
*��p���A��� ˣP�b$np�D�Ws�>�^�|❮2۪����;���W] ��CM�*:y(i���յ��Ro��
��K�
��ź�̠�g�s`��Gl�ud~�W�Oe2+�.� 䗚��>Qv`��RL���l:\��N�U�H���C.�+,5¿Ke�I#�$��s�h`>��(����8��g�K��dlo�Fݳ��ֹ�#<7HA��K�䣈�o0��hg�zoO�Q���U���{���0D�Bm�u��~<�����泽w����%��˿Y�. '|D��Y`ո2v�p��6�~z�	ޘ=,Ƹ��d�X���.?"e������q�+�rjnZ���x�/���l~�Fx0�N��Xb�"*��nH@�	A�J�����b�si6�x�^��u~�'�����������}d��'�w`֡������:�;�
���d��R��<���Q`N��Az$�C�5jOl;�|��χ밄_G���w����lx��P�E�*��Ͷ/��T���^�)W�Hxr5��gxn�ߋb�j�M��V��5�m�r���Rգg�/r�U�TԎq;$���G��a:�#^wyq�?z�a��7���(G�1QV^tu酇�WQ��u�x���k���rժ��H��*D2�g�BG�W�ڂ�dG|�U�U��|*�M�|����n�$w��x��#9�c`�e[Q��\�8��el�����C���$6%F���pc3�L�y�<~��ĔU���g�nG޳�?>j��x�GA����*�;~d���)�[+�9�ϰ�6lES$j�d����q	����ϥ�wا��)5S�ß��p8<���o���XO�>��H?%�b�>]�ߵґZ��͔�2�Q�3	�BJ�b�X��F��Fh�.�����r/9�0�
@r��_��L���2u��eU]1�Ee��C���}`��(ڪ���U�&��E�2*[��Bʏv)�)��[��*���Sk�:ŧ25c�8������\���g"�?��T��A'#t�v˱w���z��X�c/C�fcۇ'܅H/m#��l�U2�	>���~��&x=���6�쯍�q�
]�0��@'�KA��ȓ�.�W�DfxDb��V=T�o�k��ʰ���s�S��.!���h/����İ�_�����?�<��h|����/;ӥ@_�uUq�?1Q�ph$VRc�����V��?�=�r12�E�2@(X�*�����-�Qj�!*Xo���J<<�g���e"VJWݏG�)\��m�D�ʢ��d���+1���"*CV��G��r�/�n*p8C0���Q��-�A�x�A:Z�kI�ܾ�-��_-κ���<7W�J��Ŗ���F�b��C�r~0Ѡ��[��3�>��~5��F����u�lމ��D��Y�T�u}�*[PyaJ��<=�@�v��".C�Q�NF��~��T��&��xL��F3��;�����9*����Sw@Ѩ*�]��*��x���W������57/G���ad#:ⱗ��:i�L�7V� ��H���t�J�G��^�*)�3�n��Q���U�^��E=<�!�$�̿���I!�x���)Xb?酰��D�B?����Dy�R��I����c����K ���Of�*?��2��Yh������Qi��8�D�����i��;�HF�u������	/��0����3��g0���I~��7�%e�b��p�0׶�^v��(ڠ��SB�|m$hLH�)|�l�|�O������A��/N
�xJBC��r!:3]vL�V����Y�u~����m���{�QnXBĢkV�A��(�y������p3��ɯ�t?�� }mF���	�y�;UO�ث�����S�\ޗ�It���6$g#확i �����+��K�)�=�� ���j���ݵ6W4G��YFr��;��	~t��E�
���s���^��n��7l�i�$:<���4/n���\B=Z��w	/�؟����R����$����]jm�e�k!d ��>�3o��v(�Qs�_O��R�ub@$�303F�T���kY�j����p����� Dx�2\s��J��O��� ���6��Y���NR)x��)�`��Z:���	Z<�f�]H�`/�f�x��Y��������O^4� ۦڡUQ����|�l.�|�S jMu��exa��:v���4p~���@W�	.i�b���鍃�&=�3j�����`ٱ�Z�h��<O�B��,2§��,�nVp��j�aLN�\���}�^a��k��fU�S��ױ�/���n3�CS�&=�yW8 N�^|�ov�#ڜ�Q��Ӣ�}�[D�IY���*�6�4���g�A�K��ƨ����Ua��_�.����9�1>{a���"A�\*�h?u^he��u����ɏnO�<���AB���a�Nk���� ������+��#|�Y��๹�^y�g�ޒ}����H������Iwf u����d/m~��/x�}Ogx`J�S���$
� \��͢���م��з>�����k�����O���O��:��E��g�� ��ŕ�/�����;Ͽ�NL7,e��pX��c���v��������������@�<_��PJ�Sm3��ڇ�)sN�T���.c�u�f��Fe��Wtj��IpT����� J�zC���>���TP'�pхN�"<��Igx�}��3<0]XEU��	��`��a4���(1�Ҥ�t��2�2���)�����_���=Z�J���]V�0���&J��s��b�w�u`�>R�����O��V5!�/o�S�e�����6%0�TI��6�[��l��%�:��/��x~���p���3���\L��;��,	w�"g�-���^��`�6�:��/����~�m@>i�+h�n6J�k� �6:g�/��;GZ�7��� ,�6�:�����O'ρ�Ƀd,I�ލ�0.�f�ƍ��[{O�T��p���#̎ʢ�	_M�'��N���7���І�DL01(�01С���&�Z����x������R���M�;�4ǧ0M��4����_;�������`����B���t~��*�OO�W��#gCa.��3;�E�7<h�Wn�6	����7̢bF��ɬ���1d����c�X�IPt/bѾ����Qo!҂���*wea�â��X'��m@��OUt��m��R��MGj�
36�ֽ�����l�<7����.���['?��GHlãٴ<�[�&3O|�5�o2k�K�}ȸn�����r���������M��T���1I]6"M��cx@ <�rW8BEy2e������Y�k&d��򂰀_��~������s�&<��    + 7-���ٱ6�JP�&��=��!�����b�����Mj���P{`���;��M9�ܣ�e#	c���Gkq�GZ]Ag�`�͘II��ی�� ����|���[�0��P�F�ǀ����9>�IƬ�h��y�A��ê��~y؊ĭ'��lb��.f��e˂�!��9*\|ϯ��٬�aG����4p�y����#�l���C����{�;����!�+6�W>�J�_$��:���Mj���x>�*ҡ7��`^��eQG<�t�0��-û�%R��؆\'�����3���l��Z\��_p�YT9J�R�D@�U�	��y^�p�ky�����G�!*g&��Ti�ߡޱ���!��ið�Eb������_'�����G����oS��\�|�;{�G/[�"�Q�m_Ñֳ�Yd���V*���u��� >sX��N������Ӳ9����ёS/��GK�����n�)�o�p	�N���H}�|��f#x��4MUw��	
>���$܃�L��6!K���XTJ��7M�s�O�,H�ě��l�M[޶���S��7�JT�{����mz}��@t�&�xש~���^?�GR�6�3z)/�(W(��b{���?��)t/n�E�
��������d�5U#'������ �,�«ڪ��yi7�ɩ3aߎ~�����fj���F����v��8�2'*�U�:�>P��A?]>9���r�X��=�� ~��S��1���~�����%�e:T�ܲ�����	��z&�j9G��PY�~����/���ө.��jz��	L�H�0����y����`���F��ڣs����N�v�����I������y�u�@�v���/����d�GuI�Np9�һ����v:��u.���FQGU�ly�_�9˹��m�!�{E�%�:��^��~���0����?�|w���:H."Vɫ�y�gDT��rȒ~����W���K��w[}#37=��E�&)�� A7�q��f�`��<�u���c��g�<#M�4$�C�AG[!�,�{��wG�\c�l��<�v(��~���j����'08A�ݲA������<�̖�J銽z��:?'�7�y�u�7L��>v4я�<vt�I8�tUF���a'Ku�<�tn=���,s����z_��Eoϋ3��&_sH�Ԗ�4J�r�+�������#����fi9a����������Y���I{ȹTk9s�$,#H�{㢢��ɮ�!��A�W�Y�1������t��4��rsW;�,W�N7�$�������'b{��lDՒ~��p*�N%�w�Qo���T�O�G��]vE� f�\�g��v�[�����$?�v��/gz�}���>�g{i3?�"�6�]4�d����׵�V�wL�`/��hq��$�M�������S��N�B�,����&���H��=���w�l��k��L&S��ٯ�k�Ϝ|������+R�u-L5��ܵ���6*g���4)=�/ �Ғ~��v���{��lhJk9,��%��T��Kf�+�o�ͯ���$Xү�,>M}���~����ۨp[>������پd�xmW/8j��Ԧ=;��*�I�N�㔏�k�TB�SI� j�gk�G��|݃�`�Z�`���,���@�����Λߥ>K
�:�XL{rϳ˾B�h�a��Ir{���`�����@��n�*�}6E	���D4j��F����N�e��ɻd>0-��W��y��/U�׷��\g�D?f������gx��[�.�7��4��U#�[p�JC��=��$�ex�o�����&z�}��}4/0�G���w`��%Q�iC{G}q"ٻ
�����޹���_�:;@&z�����Su��i���=�4�������$X�ܲX�9�{���A,�~��j���>5���<�p��x]��)��l���5�+�;Gr6׎v�txOg��bN��G��Lo_� UXݔ5�f��KW�c弳�2l���f���o����5�~�1߳��~�郐�T�e2jh��Yi�B��i�+\��g�O�z��g��E���HSt�R�$�$d�}��ճ7��^��]�e�{�n��%U�����ka|-���x����������R2�i��F�n%@a�-��@1�R:)eE����~��X
�h���-{n�wo"���t�b�A�� n�(l����.p������2�\g��D?��Gi#��^8*��%/g8�V&�����u��J�X�B�H��6~f���֮�]`��>ki=7��\u'�@A7�V"Q\�Y��Y?C.�w8�ARk�^��u����Ԝ�~�Z�v��ᴻc�(����r�*a�p6G풶�z�:s�3}�C�0�R��/��D�f>G�����)"�;��-Fk��cy���d'=�1Y2��d���V�>=�zs�U���}9�g���.M촕U�D+Xf'a
�ws����'˱�yM�v�7rP�]:o�(���BP����Dо��lb�� �����?+"3�\�lA(��gx���`���5k$4Ɵ"2��YүS�B��l�-�4�ͽ-}�I�a'�j�վ��d���s'��\{������kY�_g7E��(��v�71���
:���;B}�S�zԶ��������dd�><l!��	�O�i��������^��fouӜӑ�1��<Jz����y��7��\[&ET�y�|��rS��}*0�$���>��R���]Pw���� um�T�
�$�k`\��oYS����K?�?���N�#��yc�G����eo<Tg�i�����U�&���8����/zT������yK�Y����	����\�(�������J�;�b'�g��^�F��������N�/���;�t`N���#�/�8���ȻZth3���\]Cr\X�)�1��E?�J��e����\�vo_O���{���+�V�:��+�O��@>��|Ui�^����D��G�[�1�����9���L�{ܹ�Z�-��o:F���>ت��R��X.�5��l��ةO�[�LJ:f��!�I(Ҿz��=�xJâ�W��.�����/���p��k��~�O32�'�4#3u���6��jT�<�T�i�D�%ρڢ�'5!��� �� �H?�Y{�1�7z�S���1�ד��i̃,z�Il�J�=\|�.�;�ۻ���衷}����y�n���sW�x�q+��mo��KW2�*�����5��������?�O�V�az��
fiT��{�zo���TS�嶆��a�,}�_�,����6�/��k�R��$��G'/T���6u�F��گ�A�&;����.���������~��3	���r��=�/�d106�31�&vo]�1��q~у
�����L�i+��"�Ix}���=^f�;^x��϶�h�����ٯ�k�K����1֍(��PY��<�>��Qݎ;%��������� ���r$��KIz?��sG#�cA�Ztꑸ�ވ1W��#`�v�~8�P�ɇ�^�[���>����ޞ�"9�[6
��u����4\i�b,8�3,�be87J?귳_M1��m-�L?m����چ�	�h;[B6��1�+�.>�[�l�-��H�l�%���3�4��=4��y�S�x=�@?���Ű�$?P�*��C���m�=6$Se���Q�i?� h��	f��vbv�)�
V�S�;�]�
��;f�;���I����v�y���1+��& ���Ƽ7�|��yIX�^�&/J�"�k�-�:9:����*�a��7觏̊��^X@NU`^�5�*�D�\eʢ_j��[�wGp�����m�<국����D�����O��tk��W[&j/�z�H�=>��k��f)���>���"V������z_��kBo#��Va�{\�������.�k��^�*���/�X���ӛ,�G�)7�>��e������A�\�P)�`m�{�v�Xp�l�(��*q���-��h9�bm�[���*�/z�����(3PM����Q��^    ˞�56���b��s~)�\�y�A�2/����w
�/q2��ق�/E��*m,q�z�+C~��G�0ѫ�d�b�*\�u��~�{ɫ/��)�`(�^q�ު^֐�U4g�����~�vӖ]){u��Iۢ.Xa�Y�R�g�m�g�	���{~;�ub̑>�<���&��
8�y�j�	*rwJ�^^t��ø*���lO���.�9k�<�y�^~�k
S��=/X�
j �(�"�%z��-�.#����;��S����ى�TDd�T��^ѿ&���P�ٞQ��󧻿�����z(&z�Y�}�2�e�/,��}_�r?��~��cI-C~'D�X�Ɣ�ߟ������x ���ò��6@�>���D?On؈c�pʋ�{�׸`1�������Q�� � �j�_X�����E?k��VF��1���e\�"�Y7A�;�*fAu�.ھ�l��?��=�ɯ��}:f-:5g��6�PP`����n�޳�}������x+÷�݄���׎������h����<w98X�-���^���IߦB���_��7]�=����3�_�3�Mz�W>"d{�E�8�e��,�Qa����:���{�PCX��
Q����T�^	�r�S�B>�򁚼؜���؆�^���	J�e����,ؔήl#�S��=���_�����[k�Y9W���b��[?B��pV���y}����ě^�ׇ�2�"����wom��"�׭����T ��t+L.d�Np`���wKc�F�}t=o�e|O�yW�o�gz�צ斑�Ļ@�Y�����Ğ6o�4\������P�m�Rs�QLp�u���tz?�Z��m�2~�k��[���4ǚ�V@rFt�j�\v�1?���PKzbzD��ط8'�>@i���#{�|e��nݒ">�{�,Ըv�z=���7� �52�n��c[Ad��S���o�
;z�t�)Fz�(5٩�kw���C7Qr �A����Π���p9�Xүqkuj�f��?i�Xj�Xlx���bK�V�e��$��+M�lbnnF>l�Y���q._�؜�Y��T��9d�)�'E}�*�����p��� w!:���de���u*"=�ޟ�E���9�	�2/��nv��������*��Ϣ�(Z�e^K�skG+���=����q��{�-Hs�����T�)��|/���i���7�'W�G�o5�g���GKJ�;�hn��;<�0��"V���q�)�s{�����:�v���m���oL���64��Ý�2���N/�.�W�b����s:�q>H�"K�u�����g�M{�����%�Դ��e|����E����<?�z��g�8�k�k�U|m�K�����^�Tt�{�{���FDlpy�ʍ'^��a�zY��sRo�Ei�Ԑg����Ys�y2[�⨦���r� �ҝ�Ԩ}�ut�|�w�Z{�n/-gzp�N�GvbL[�����pdrS.�0u�mHU�DBM �R��
A~��+�.��?���wr���>n�Doϟ���YD9X2D�AI[ ��D�@��g7&���r��^��~�y���z������4������ݮh!����������"��]�`���1�U,g��Q��j��\�������j���nʶ
N��-W�{���%·��*���f�uQ�z3T�.j1�1�BD��"nc�C��� ��x(/:�m�"-�Q�:o'���gn5j�1��T'��,>`���� �ȫԜ�53ܟ�k�zUZ�~U�)�j/�����~^p�Şu���
z/��<)}�zq�����qH�ݷs����OuL����=.uWV�i?�-v��5�zR�^/�pb�9���!�B�T�u,�����QSW���+u|5d=쌿(����� �UXT<$F�g��v�e%�s���j����(��9���\ц�֦�Ti\���� 6����T�Q�b,ki�j�Ӧ��}h��V9��6:	#�)x\�E"��F���<��3I��b����������iۆ[      �      x�3�4�2�4�2Ҧ@ڈ ?F��� ��[      �   !   x�3�4�2�4�2�&@ڔӘːH�=... ���      �   A  x����n�0�sx�z�N�8=H&�@�*�@��b��­�n=�=�M����lO��/3�V��H��X��ϿlC!r�t9O7׽�@Y�cL�(Ҭ?
�˼��!s�q�7eB~3��<77�9��v�.'QN��ʲ��I�6g�\�XKC]Un��$M[��9,	@�P!X�X-�>;�]:ՒW���Sђ)P��}�t�W����]r��B�=��|���ک� ~�Rm�:����*�̂��"����b5�bQf�����󗏅��x���[�OB��Ќ�V�XzG�j�z���fF�y�c���/���u$�j�Š�&�I�9��Y�bޥ|5_x��3A��0gTuCwE�ՍX�*L辘WN����(�����FN�����䖣��Gr�Yf_?9fΨ1���vഭxm��kL-�6e G����dx�W��J��'l�'XDPz�'7\� ��RӠ�#J!B��{;�\���P""�� ��DG��eEF�KΊNz��YXZ����׭bv�6-�37�A}��^�W�e�����"���������ɍ��N�{��d2� �UX�     