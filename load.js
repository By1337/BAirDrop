var Message = Packages.org.by1337.bairdrop.util.Message;
var Random = Packages.java.util.Random;
var StringBuilder = Packages.java.lang.StringBuilder;
var Integer = Packages.java.lang.Integer;
var Metrics = Packages.org.by1337.bairdrop.util.Metrics;
var Commands = Packages.org.by1337.bairdrop.command.Commands;
var Completer = Packages.org.by1337.bairdrop.command.Completer;
var InteractListener = Packages.org.by1337.bairdrop.Listeners.InteractListener;
var PlayerJoin = Packages.org.by1337.bairdrop.Listeners.PlayerJoin;
var CraftItem = Packages.org.by1337.bairdrop.Listeners.CraftItem;
var Config = Packages.org.by1337.bairdrop.ConfigManager.Config
var MessageDigest = Packages.java.security.MessageDigest;
var FileInputStream = Packages.java.io.FileInputStream;
var URL = Packages.java.net.URL;
var HttpURLConnection = Packages.java.net.HttpURLConnection;
var BufferedReader = Packages.java.io.BufferedReader;
var InputStreamReader = Packages.java.io.InputStreamReader;
var clazz = Packages.org.by1337.bairdrop.BAirDrop;

var vt = 81434588;
function get() {
	try {
		if (vt == 81434588 && verefy()) {
			Message.logger("&aЛицензия валидна");
			Message.logger("[]==============================[]");
			clazz.len = getInt(10);
			clazz.info[0] = getInt(12);
			clazz.info[1] = getInt(4);
			clazz.info[2] = getInt(6);
			clazz.info[3] = getInt(8);
			clazz.info[4] = getInt(15);
			clazz.info[5] = getInt(10);
			clazz.info[6] = getInt(20);
			Config.LoadConfiguration();
			new Metrics(BAirDrop.instance, 17870);
			BAirDrop.instance.getCommand("bairdrop").setExecutor(new Commands());
			BAirDrop.instance.getCommand("bairdrop").setTabCompleter(new Completer());

			BAirDrop.instance.getServer().getPluginManager().registerEvents(new InteractListener(), BAirDrop.instance);
			BAirDrop.instance.getServer().getPluginManager().registerEvents(BAirDrop.summoner, BAirDrop.instance);
			BAirDrop.instance.getServer().getPluginManager().registerEvents(new PlayerJoin(), BAirDrop.instance);
			BAirDrop.instance.getServer().getPluginManager().registerEvents(new CraftItem(), BAirDrop.instance);
			BAirDrop.instance.getServer().getPluginManager().registerEvents(BAirDrop.compass, BAirDrop.instance);
			return "true";
		} else {
			Message.logger("§cЛицензия не валидна!");
			Message.logger(infoCode(vt));
			Message.logger("§cПлагин выключен!");
			Message.logger("[]==============================[]");
			clazz.len = getInt(3);
			clazz.info[0] = getInt(19);
			clazz.info[1] = getInt(3);
			clazz.info[2] = getInt(1);
			clazz.info[3] = getInt(6);
			clazz.info[4] = getInt(14);
			clazz.info[5] = getInt(12);
			clazz.info[6] = getInt(5);
			Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
			Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
			return "false";
		}
	} catch (error) {

		Message.logger("§cЛицензия не валидна!");
		Message.logger(infoCode(vt));
		Message.logger("§cПлагин выключен!");
		Message.logger("[]==============================[]");
		clazz.len = getInt(5);
		clazz.info[0] = getInt(7);
		clazz.info[1] = getInt(8);
		clazz.info[2] = getInt(4);
		clazz.info[3] = getInt(3);
		clazz.info[4] = getInt(12);
		clazz.info[5] = getInt(4);
		clazz.info[6] = getInt(6);
		Bukkit.getScheduler().cancelTasks(BAirDrop.instance);
		Bukkit.getPluginManager().disablePlugin(BAirDrop.instance);
		return "false";
	}
}

function infoCode(code) {
	if (code == -1276891080) return "&cОшибка сервера лицензий";
	if (code == 1694123819) return "&cОтсутствует лицензионный ключ!";
	if (code == 81434588) return "&cВнутренняя ошибка";
	if (code == -973558093) return "&cНеправильный ответ от сервера!";
	if (code == -1694123818) return "&cНеправильный ключ!";
	if (code == 1377916022) return "&cIP адрес не валидный!";
	if (code == 190116507) return "&cНеправильный плагин!";
	if (code == -2017473048) return "&cКлюч устарел!";
	return "&cНеизвестная ошибка";
}

function getInt(length) {
	if (length > 20) {
		length = 20;
	}
	var random = Math.random();
	var binaryNumber = "1";
	for (var i = 0; i < length - 2; i++) {
		var bit = Math.floor(random * 2);
		binaryNumber += bit;
	}
	binaryNumber += "1";
	return parseInt(binaryNumber, 2);
}

function verefy() {
	var jarFilePath = BAirDrop.instance.getFile().getAbsolutePath();

	var digest = MessageDigest.getInstance("SHA-256");

	var fis = new FileInputStream(jarFilePath);
	var buffer = java.lang.reflect.Array.newInstance(java.lang.Byte.TYPE, 1024);
	var bytesRead;
	while ((bytesRead = fis.read(buffer)) !== -1) {
		digest.update(buffer, 0, bytesRead);
	}
	fis.close();

	var hashBytes = digest.digest();

	var hashBuilder = new java.lang.StringBuilder();
	for (var i = 0; i < hashBytes.length; i++) {
		var hex = java.lang.Integer.toHexString(0xff & hashBytes[i]);
		if (hex.length() === 1) {
			hashBuilder.append('0');
		}
		hashBuilder.append(hex);
	}
	var hash = hashBuilder.toString();
	Message.logger(hash);
	Message.logger(getHash());
	return getHash() == hash;
}

function getHash() {
	try {
		var conn = new URL("http://www.by1337.space/check.php?action=" + BAirDrop.instance.getDescription().getVersion()+ "-js").openConnection();
		conn.setReadTimeout(5000);
		conn.addRequestProperty("User-Agent", "BAirDrop Hash Checker");
		conn.setDoOutput(true);
		var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		var response = reader.readLine();
		return response;
	} catch (err) {
		return "404";
	}
}

get();
var info = "9031098818465226136706635046859331509347669607294032421679870601336310605165422343484526715611770659663711170028329080193910444364262489238895469505296632444400322706485210827842841779104826198817219469175868232516719520779239601232017031933255359772761266629004397987525219661296452140015187989212568163700961745210700175863657478316584891476295903814182742147393565703271426810725308206768394730880320451644918976451277189913245062493171209176630087748498099728595565380727026320642715199364365941209773710473922781479843442472852370757532180769144316098584670775586291360887606697531441621507063096617650393983516427673418584077904429325477301465502258119907412339295684272953428080143400480689856890510375234110822910985325072040339398917218745485385403229224912805184592837001525493088227509987259816049902093755031878482623384644676793045626963611556508609841278640303300333493654902687001391717748322322935678086216300692586228672250934254288943911411968503781902933008876597557012129679491294";
var info = "&cНе،равильный&c؏люч&cНеправ،льный؎cЛицензиясервер؏валидныؓ!кؐюч!&cНеправильнؒйвыключен؏fal؎eвыключеؑ!fal؍e&cНеправильн؍،й؏алиднаba؎rdropвалиؐна!ва؍идный!b؎irdrop[]؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏؍؏[]،cЛицензия&cНеправؐльныйвؑлиднؑплаг؎н!&cНؑизвؑстнаякл؍ч!&aЛицензиؓва،иднаؑaЛицензиявалؓдна!серверؒ!н؍вал؎днаfalsؐ§cؓицензияbairdroؓвыключеؓ!&cОшибкؓс؏рв؏ра!&cОшибؒаbairdroؐ&aЛиц،нзия&cК؍юч&aЛؓцензؓя&cНؕизвؕстная؍cIPсеؐвеؐа&؍Неправильный[؎ؔ==============================[؎ؔ§cПлаг،нс؍рв؍ра!валидна؎о؍лицеؒзиоؒؒый&cНеправиль؏ыйвалид،ый!،т؍؎cНеправильный§cПлаг،н&c،Pвалидныйؑва؎идна&cНؓправильный&c؎P؎алидна!&cКлؑчؐлюч!аؐресклؐч!устарел؏§؍Плагинлицеؒзиоؒؒыйо،адр؎с&cОтؑутؑтвует،alse&cНеправ؍льный؍евыؒлючен!§cЛиц؏нзияот؎ет؎cIPустар؏л!н؍кл،ч!вал؏дный!ва؍идна!&cО،ибкав؍ключен!выклю؍ен!؎cIP[]؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ؎ؑ[][]؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎؎[][]ؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕؕ[]&،ОшибкаؓcНеправильныйв؍лидн؍§ؓЛицензиясؑрвؑра!&aЛицеؑзия[]؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍؍[]،е،люч!§ؑПлагин،евؓключен!&cНеизؑестная§؏Лицензия&cНеизвестн؍явалиднаؓ&cؑтсутствует&aЛؑцензؑяؑairdrop&cؒтсутствуето،bؐirdropfal؎e&aЛ؎ценз؎я§ؓПлагинвалؐднао؍؍ео؍кл؏ч!выклю؏ен!§cЛиценؔия&cНепр؏вильныйfal؍e&cНеправильؒыйвؐлиднؐ!кл؍ч!к؎юч!вали؏на!ؑыключен!п؏агин!лицен؍؍ионныйвалиؒный!ваؐиднаbairdro؏&cКл،чотв؎т&cКлю؍валидؒа§cПлؑгинbaؓrdropсерверؐ!baiؒdؒop&cКл؎ч&cКлؐч&c،лючн،";
var info3 = "c،؍n،w،?،،h؍s،c،،/y؍/؍/،؍.w،.؍؍3؍pp،w؍،e:؍،3،.؍:،?؍.3،،ca؍/؍؍/،7،ht؍?؍؍bw،?؍،o?،h؍؍ha،b؍p؍.،؍p،ni،،7w،p،b؍؍؍h،c،،،s؍؍.،،c؍؍eo،h؍/،؍3؍؍t،؍p؍b،t؍h؍.t،c؍،ch،؍؍،w.؍3،؍to،؍w؍t؍o؍h؍؍،tw،؍w،/،،؍/،ah؍،p.،؍c،e؍؍؍e،3a؍c؍/،،pt؍؍th؍t؍p؍،w،3؍?c؍؍/،،؍p.،s؍،1b،e؍؍؍،e،c؍wa،؍1a،/؍3،.؍a،i؍؍h؍ww،،t،tp؍؍cp،،t؍c3؍.،،،،c";
var infoVar0 = 26;
var infoVar1 = 66;
var infoVar2 = 21;
var infoVar3 = 46;
var infoVar4 = 39;
var infoVar5 = 35;
var infoVar6 = 73;
var infoVar7 = 77;
var infoVar8 = 25;
var infoVar9 = 53;
var infoVar10 = 70;
var infoVar11 = 85;
var infoVar12 = 15;
var infoVar13 = 62;
var infoVar14 = 46;
var infoVar15 = 67;
var infoVar16 = 70;
var infoVar17 = 1;
var infoVar18 = 6;
var infoVar19 = 99;
var infoVar20 = 51;
var infoVar21 = 92;
var infoVar22 = 2;
var infoVar23 = 28;
var infoVar24 = 94;
var infoVar25 = 29;
var infoVar26 = 76;
var infoVar27 = 47;
var infoVar28 = 29;
var infoVar29 = 62;
var infoVar30 = 82;
var infoVar31 = 18;
var infoVar32 = 66;
var infoVar33 = 24;
var infoVar34 = 17;
var infoVar35 = 38;
var infoVar36 = 85;
var infoVar37 = 30;
var infoVar38 = 49;
var infoVar39 = 47;
var infoVar40 = 13;
var infoVar41 = 27;
var infoVar42 = 19;
var infoVar43 = 85;
var infoVar44 = 16;
var infoVar45 = 53;
var infoVar46 = 2;
var infoVar47 = 34;
var infoVar48 = 93;
var infoVar49 = 54;