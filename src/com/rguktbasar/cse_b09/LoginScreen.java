package com.rguktbasar.cse_b09;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginScreen extends Activity{

	private EditText username, password;
	private TextView wrongPass;
	private Button login;
	private HashMap<String, String> credentials = new HashMap<String, String>();
	SharedPreferences sharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		addCredentials();
		
		sharedPreferences = getSharedPreferences("cse_b09_login", Context.MODE_PRIVATE);
		
		
		username = (EditText)findViewById(R.id.username);
		password = (EditText)findViewById(R.id.password);
		
		if(sharedPreferences.contains("username")){
			username.setText(sharedPreferences.getString("username", ""));
		}if(sharedPreferences.contains("pass")){
			password.setText(sharedPreferences.getString("pass", ""));
		}
		
		wrongPass = (TextView)findViewById(R.id.loginStatus);
		login = (Button)findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				String user = username.getText().toString();
				String pass = password.getText().toString();
				String cred = "";
				if(credentials.containsKey(user))
					cred = credentials.get(user);
				if(md5(pass).compareTo(cred) == 0){
					if(!(user.compareTo(sharedPreferences.getString("username", "")) == 0 && 
							pass.compareTo(sharedPreferences.getString("pass", "")) == 0)){
						try {
							showDialog(user, pass);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						Intent success = new Intent(LoginScreen.this, Homepage.class);
						startActivity(success);
						finish();
					}
				}else{
					wrongPass.setText("Wrong username or password!");
				}
			}
		});
		
	}
	private String md5(String s){
		MessageDigest digest;
		try{
			digest = MessageDigest.getInstance("MD5");
			digest.update(s.getBytes(), 0, s.length());
			String hash = new BigInteger(1, digest.digest()).toString(16);
			return hash;
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return "";
	}
	
	public void showDialog(final String user, final String pass) throws Exception
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginScreen.this);
        final Intent success = new Intent(LoginScreen.this, Homepage.class);

        builder.setMessage("Do you want to save Password: ");       

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() 
        {
            @Override
            public void onClick(DialogInterface dialog, int which) 
            {
            	
            	Editor editor = sharedPreferences.edit();
				editor.putString("username", user);
				editor.putString("pass", pass);
				editor.commit();

                dialog.dismiss();
                
                startActivity(success);
				finish();
                
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() 
        {   
            @Override
            public void onClick(DialogInterface dialog, int which) 
            {
                dialog.dismiss();
                startActivity(success);
				finish();
            }
        });

        builder.show();
    }

	
	private void addCredentials(){
		credentials.put("cse", "271226cb355bdda491d38bfaf40f675d");
		credentials.put("B091029", "f12aa8ab7134316fc3c339aa6db84575");
		credentials.put("B091066", "6e9652f631ac97a06930dc9a735955af");
		credentials.put("B091068", "39962d776e98cb33fbbe47c84c243e6");
		credentials.put("B091165", "fa3129c1ebf0cc672e908f79fb744200");
		credentials.put("B091169", "36cdb8d13062a9a41a7eab1680b1d92b");
		credentials.put("B091174", "d3832d6f2dfa82ce4fae4b602bf41bad");
		credentials.put("B091182", "391f6e95a3b0a8011ee5515eb473b3b9");
		credentials.put("B091205", "9826c77effae3a4ba96da3a8dbd4eacb");
		credentials.put("B091255", "3fdb0e39dc639a3c453198394c1e344f");
		credentials.put("B091303", "1e7f964c220fd6e5b517a9647c9f447e");
		credentials.put("B091305", "4ae43fd3729a02cc803e3a71c6197f33");
		credentials.put("B091379", "f9977f167c3dad75a404f3271e14f3f5");
		credentials.put("B091412", "b6a15b552b2e3b2a93a8d369d255ac30");
		credentials.put("B091448", "58dbc979ed8e04583e9a7f4ffdccc882");
		credentials.put("B091477", "1d704b581011f2c2739b6f28a5cbd8c3");
		credentials.put("B091486", "e7cfc3fa140e6c3fe0c893c966732f4e");
		credentials.put("B091541", "bde201ebff714aff13e6453c6a47b231");
		credentials.put("B091600", "85f73ded77b974fa5172ac6f9972befd");
		credentials.put("B091615", "c51722d35b697aaf591f6066d68b57bd");
		credentials.put("B091653", "b4dedd072c54dbd5f4953489bdf30359");
		credentials.put("B091660", "ac48f6c3a421c31b6d6e73b219d64858");
		credentials.put("B091681", "9a7f6b0414a99ddfa3efd6400a7cb6ff");
		credentials.put("B091684", "117904b1f0188f5bbd60f4906557536c");
		credentials.put("B091738", "b1852aae5a55cd4dd1c0a58821259b4a");
		credentials.put("B091759", "59ed2097899ee86c61f9780106fad152");
		credentials.put("B091783", "d270f3ace917ee30dbd7b7a02e103124");
		credentials.put("B091812", "de0fa0551d17838d9f634875a58b96e5");
		credentials.put("B091867", "dff4967435bd3574cba05a135bf2eacd");
		credentials.put("B091876", "aba235f61abfd102406cf0666c5f84f6");
		credentials.put("B091890", "12f84e0282f69d078f583564852beba9");
		credentials.put("B091923", "ca1a5d9b74a7b48c5dd8d986eaa0659a");
		credentials.put("B092002", "9b1ad8d12b94d7a7c37c026db9ed35af");
		credentials.put("B092026", "d543eca8c626ce763679fc517bcb1a1f");
		credentials.put("B092036", "b93275eb54bff2e668f559dda2c56d5b");
		credentials.put("B092039", "11b9de03e7a8c26f9a54fcf4721e045b");
		credentials.put("B092059", "9456f1bc3ac7f9cf6f61405beca39923");
		credentials.put("B092063", "120ea5a47eed425e82366adc27eb42b0");
		credentials.put("B092068", "7c2841f33fe58cea44cf270c9eba0dd9");
		credentials.put("B092092", "b350b03f82d3a233b26798ebdd7e076");
		credentials.put("B092104", "cb8f4472af30ccd3e992d37427bdebae");
		credentials.put("B092127", "e1b9760c11e5cdc68ed249c9f6ad5049");
		credentials.put("B092217", "61aae07deefebffe07c5186071897b5b");
		credentials.put("B092225", "3ae8710853ecdbe090c5a6f7cd0ceb4d");
		credentials.put("B092253", "c5ecbe1f020629f45674380c0128ab06");
		credentials.put("B092305", "8a73286007db760bc08bdffaa6084113");
		credentials.put("B092321", "26d5c09c6130aed22919f1c92c7ae8e9");
		credentials.put("B092335", "15631e1efc9dc502120506959007447b");
		credentials.put("B092420", "9b72640b3326942f02ca07e815a6abaf");
		credentials.put("B092421", "34e4f685a6e9f60ca145d6257e252932");
		credentials.put("B092426", "2a45e12fc408c4afa89c8793f2fe50df");
		credentials.put("B092494", "5bbaa42e5ce56b0fd9f044ef872a0218");
		credentials.put("B092508", "c32c5278cff3b31c7d8d77195a4d0a46");
		credentials.put("B092523", "35b48bec394df5fcc5145b5bd9e56dbf");
		credentials.put("B092536", "62dde01699ff21dbe3abcc0a9965e683");
		credentials.put("B092543", "7e7eb980993be5ed08c46072e38b4b62");
		credentials.put("B092583", "f3d023cf49163f73cd046bbf4d2f9c76");
		credentials.put("B092634", "62294ac164efaabd083185e78b1d8359");
		credentials.put("B092636", "a672c60ad2346abd058139ee9082b90c");
		credentials.put("B092661", "e7dd5ea462d4088aeb699f50c96499d4");
		credentials.put("B092681", "529a5e27840fc0263ae940b1d742e8fa");
		credentials.put("B092720", "d19cfa59afb11bb3367651c3623c5bab");
		credentials.put("B092779", "3ffb382902b26276f123724a513e3b7");
		credentials.put("B092791", "e3af454b9efb77b4a3700e94d3a5bc48");
		credentials.put("B092946", "4b0f8eb22b1a9372b399af768e8367c");
		credentials.put("B092949", "fe7b18cd1f1fae09b9c214ae67e9c816");
		credentials.put("B092952", "f3d2aadb520ea683bccdf468d655442f");
		credentials.put("B092992", "5771722ede84faef0b2d28c5fd67166f");
		credentials.put("B091010", "b5081a326f55d15d64f4c836db0622cc");
		credentials.put("B091052", "bb3c8f66d18b60c05cb4f98848ee8ce3");
		credentials.put("B091055", "bd123e0990cceda8114f238d21cf9f4c");
		credentials.put("B091072", "f3d3f5ac28bb91480363a71c221bc5fe");
		credentials.put("B091086", "6477ea7746aafb54266d86ccc156981c");
		credentials.put("B091118", "2bc1c96260568240ad07c1f0f9aeebc8");
		credentials.put("B091171", "d0041fd3d807c5207fbcf430405ce2b2");
		credentials.put("B091207", "66b8d1b660369804e84fdc90d16882e7");
		credentials.put("B091226", "cfb90372ea0253acdce4a4e8e7e1ffc0");
		credentials.put("B091280", "8f7091d777688f37c15ebd61711e7c2d");
		credentials.put("B091432", "26b45902f082ecf48edbf0237a40f880");
		credentials.put("B091473", "4ae4dc3a1dc7b7a188eb08e025cc0d1c");
		credentials.put("B091490", "9e04f4dc2c7eeb675fc27762fa4373e3");
		credentials.put("B091526", "203d1455c91a382ba54cbe5ad712641");
		credentials.put("B091559", "ce6f8ac5425f9506b4e81689a1ecf11a");
		credentials.put("B091601", "fd7b0ff07fe76aba36877ca798c68d10");
		credentials.put("B091628", "a60b156df9f1562ff5bc24de6346187c");
		credentials.put("B091707", "376bc90bcf7d0cf955969f2db0fcc142");
		credentials.put("B091718", "f58b9857b0a03bbc816e0002a3bb8a56");
		credentials.put("B091762", "5d51b989b78e3d37e56be5ed1a0b9c1c");
		credentials.put("B091767", "a78d6d42ffb4369625b2670f9a2c5471");
		credentials.put("B091772", "79b006d4ee55b7c08912ca865a2f4c4f");
		credentials.put("B091889", "e012a2af8ae74f03b03dfc8afdaa90d5");
		credentials.put("B091893", "3736ca69d0c8c6ee3592fba9614c668a");
		credentials.put("B091895", "21f80be651b94edb4c957dcd2b30096b");
		credentials.put("B091918", "29398afb1b645568456d02ce336294c1");
		credentials.put("B091942", "ab625a94a61d18168b940fb054b9e5cd");
		credentials.put("B091956", "27a2364c43c363883854171979542b7c");
		credentials.put("B091971", "c2c23b4d33bd425a2c20faac1e3215a5");
		credentials.put("B091975", "ab31dacf42a3e27508023e5263353a8");
		credentials.put("B092027", "b2324746341c25ad3173e0bc7268cfe2");
		credentials.put("B092044", "55de4741f73fb57ec7c742971393a424");
		credentials.put("B092080", "97b9421ba9e3f7f9e01f9f0b27f082da");
		credentials.put("B092088", "971e92149f8c7065fbd3210d42ab282e");
		credentials.put("B092106", "4daa1f3bacda10191e1f9b2dbf1a0355");
		credentials.put("B092182", "853d6a0617c6ba65a89f9a75a184cb05");
		credentials.put("B092228", "36147dcb264e99fff93e226fd05d7747");
		credentials.put("B092276", "c5ca3ed15226b0e154951e08b4a69652");
		credentials.put("B092284", "8e468dd077a76319656f98019b5b7675");
		credentials.put("B092306", "6d2416c2fda1178fc1748befc5d33686");
		credentials.put("B092323", "5710d4fb8c61487b373eec96e2581b");
		credentials.put("B092337", "215480c08ae919e048a3ca5bfac639d2");
		credentials.put("B092384", "b511e193ef12dc62027897e646f491f7");
		credentials.put("B092476", "21d93b5454ea9608fa4231aac74b3680");
		credentials.put("B092498", "cdf5a8d9651f3bf6a8d24aa892ce7409");
		credentials.put("B092513", "7874d86096d3ae0a95ce3cec0d2ff8e4");
		credentials.put("B092530", "e837cb979d2b91f4b30694a2aae7e7e9");
		credentials.put("B092632", "76922ce76558d5b08b1f1b3cb187bf58");
		credentials.put("B092655", "e3ab23fbbb3e5e42775d082021b5755");
		credentials.put("B092680", "f467ab3590e5bffe68f68b6882e4f25e");
		credentials.put("B092685", "5592269149682796f26b18f77e7d2fbb");
		credentials.put("B092686", "6c757d29104cc41bcb70e085c5550322");
		credentials.put("B092691", "d5f7551c45aa2693210b1787677bce2b");
		credentials.put("B092700", "2a1d1f3116f2123a72ea5553369f9e33");
		credentials.put("B092732", "71de8dcc6be27a4095bbeb8cea85d919");
		credentials.put("B092741", "4e51a5a5906a6ab74f6a1015c36aee5e");
		credentials.put("B092771", "6367e834cd96dd19dfc5de76c10eafa3");
		credentials.put("B092782", "b7630cf26cb5aa8a3d6bfe5db81aed80");
		credentials.put("B092798", "81533cf060f76e47f7daccc697cccce5");
		credentials.put("B092824", "b87f9c084c5ac4a3d782e46e0f3e4cbd");
		credentials.put("B092854", "25c8a163774b74f31a1c34285cfac9a4");
		credentials.put("B092918", "d536e380b31d1453e3d7ca6c6c77ea3a");
		credentials.put("B091044", "1fe94cab3064061083a1f0880cf1eca5");
		credentials.put("B091074", "f9344a4d4bbcd5924d9d06e2a8ad01ef");
		credentials.put("B091108", "d94ca5b20f6577f33c939d3798f39ea0");
		credentials.put("B091125", "b892568bda45e8d10bda64b00689ac21");
		credentials.put("B091152", "70868fe8f565c7e9df4d5631cbfe8a89");
		credentials.put("B091155", "bb4d98db4938f6df2ffcbc6cb4392fd1");
		credentials.put("B091180", "d98f543024c41bbb1b29927c63e6b0dc");
		credentials.put("B091189", "34db57f00e959fd07a06f7a88127ddc7");
		credentials.put("B091200", "3fd17ea0636a4e257f8af837bdaec920");
		credentials.put("B091216", "a5be3641e6909e0d9be73d3dd1a84db7");
		credentials.put("B091239", "256fa20fde1782e6a2e405693a154");
		credentials.put("B091240", "f6e84ad96c83d2f7360899bebc025101");
		credentials.put("B091258", "744f4568ea4afa4475fe9fd1927bc6d5");
		credentials.put("B091259", "97545e7e8afce55c81aee0a452b3a488");
		credentials.put("B091381", "57fb774b0510048a8f01e6c72f65be0f");
		credentials.put("B091419", "58938dad95fe2f591d903c0e561f044e");
		credentials.put("B091440", "72a0a1344f056061b6a5cdaaffa662e3");
		credentials.put("B091457", "180b9d07390d50808e1ec609ed361f8");
		credentials.put("B091497", "dfc6caeca0073def42208740987e7aaa");
		credentials.put("B091501", "edc2d855a38f6e77c271b6d1b71f557c");
		credentials.put("B091529", "b519b01a5378f2078e84c7b2c0e4c45a");
		credentials.put("B091618", "2a68f5fe0507d38fa1c7049af05d61bd");
		credentials.put("B091663", "1132bde06d33649c81142f6aa6e36baa");
		credentials.put("B091685", "ff7e7d10288a9e83fca6001009de4b1");
		credentials.put("B091714", "41af588ca2a1f3b4d215bb5182224a64");
		credentials.put("B091736", "b83c6023d1de12861b467e5636384ebe");
		credentials.put("B091745", "34b558df4bec409270ddf3dec1247690");
		credentials.put("B091763", "4ae37f016c83385e980cecd04d67b052");
		credentials.put("B091775", "731bacb824bec6969b40b34872983a8b");
		credentials.put("B091808", "19686a27211fe9fc61af95ad570e9299");
		credentials.put("B092014", "48099d2a188c62e8833641e880f5eb2f");
		credentials.put("B092028", "728bf9e5cb7142ba0289650d1c7ff3e7");
		credentials.put("B092051", "4aea723a1130bf5fb2626784d5434c30");
		credentials.put("B092136", "ea2ed6bf755422ef4f08b3bd2f684b68");
		credentials.put("B092212", "c380d17617c021e55d15336468d0f8ae");
		credentials.put("B092230", "a63d7c069f432357955d85a4abc872a9");
		credentials.put("B092231", "cca67dcd58822cad4b7944b365cce19");
		credentials.put("B092233", "92b68ae9b81f255b6a3cabb9773ee08a");
		credentials.put("B092248", "f54a552ef3cf9d21041f4ad33fcd76a6");
		credentials.put("B092254", "c75f800e31c3f7d1a668e9ba6c6394ff");
		credentials.put("B092291", "694a11dce9d5e43c744ed956032d34f7");
		credentials.put("B092307", "ea6788126f9d9f730222cfe1375bdb1f");
		credentials.put("B092326", "bacdcf425852cd53ef5d74e9fec46e4");
		credentials.put("B092379", "624c5867033aa45c65785535046002b");
		credentials.put("B092386", "650e1fd79a6397043db58b377cbe5ef2");
		credentials.put("B092404", "38021f7f137203e3a442fd9b2252908e");
		credentials.put("B092410", "be789b9e825d1c1d46f6dec103590b3");
		credentials.put("B092440", "ace657fac97cbe6c9397236f79e21417");
		credentials.put("B092464", "22c63bc09e43b450045a55f1dac78c89");
		credentials.put("B092545", "32b83450d8cbdedb56d015e4b727820d");
		credentials.put("B092565", "1bf7fe51d320c4685a27cccff067e09b");
		credentials.put("B092623", "9ec2658a0442eec00a114b865d9b2d3d");
		credentials.put("B092654", "f63d7329518af15514f78a73453a1212");
		credentials.put("B092658", "450bbcbb89765c1747618d2698d57311");
		credentials.put("B092723", "93d1b2f850aedbd2e1202a920f595d26");
		credentials.put("B092745", "4a1d8bd0740bed23d8350fe19a4569d");
		credentials.put("B092764", "b64d3aea7ebf3e6693a820c50452f2b0");
		credentials.put("B092772", "98dd1f0b79a483025340df6b87ef6f39");
		credentials.put("B092843", "f37699dfdad458ebc00986e18f92b634");
		credentials.put("B092868", "ce781dadd91f0189112039b1924dd6f8");
		credentials.put("B092891", "52698b6e6f856fae8518f7c05d98680f");
		credentials.put("B092963", "25d4470948d113ddbee766d73110c15b");
		credentials.put("B091014", "e0b43d5fa8112ba2114b8c397902f6e8");
		credentials.put("B091053", "59a288d9097d3756b5bbb3fb79add191");
		credentials.put("B091173", "44461e9455a22655991fa4fabc6e465b");
		credentials.put("B091190", "991307371210345590de2b11453fdbce");
		credentials.put("B091220", "ab148c2b46e82e7c5bf4299ef592784e");
		credentials.put("B091270", "52cb623bd3f99758b120c93e83b63c8c");
		credentials.put("B091282", "3f0827a27d5e0c46d12fa08ba48d8268");
		credentials.put("B091296", "b68fa67d7dc257a10b79c29d40f4ea4e");
		credentials.put("B091332", "46c8334cfb11d622ccbaa267cf3f73");
		credentials.put("B091339", "17a0aab1d18816e2e85c9e19698957cb");
		credentials.put("B091384", "2683248b98960659b6618012367d35a5");
		credentials.put("B091415", "b477f424e0f772436905324ddad93676");
		credentials.put("B091444", "55a4334e604c7e9546d639a177674e3b");
		credentials.put("B091485", "770f146c60093897e2c569badfa881fe");
		credentials.put("B091505", "f3419e93ffa116c4222cf9b3d8678581");
		credentials.put("B091534", "8a3d099838af0534731fa3644ab310a2");
		credentials.put("B091578", "5dfe3ebbc2310ecb6f62c96ff3d9cb0c");
		credentials.put("B091607", "2f6fe1382f7b52e3a6f01f723460108a");
		credentials.put("B091742", "477c70818c8c92835232c161dea36b6f");
		credentials.put("B091765", "eaadf865f4d002c843ba8b6c3fd6f7e9");
		credentials.put("B091769", "345259f06996bc7805bb0af665757962");
		credentials.put("B091773", "25ec92575574fa0b9c1db73b5a61be71");
		credentials.put("B091780", "7cf463cae9469474c61d9a8d9ecf9baf");
		credentials.put("B091805", "cb668a03332590998ecfcd4c3541d97e");
		credentials.put("B091811", "4455bcbe17ca36e56c3883bb21d546e4");
		credentials.put("B091815", "e1334c56225b22e31ddfd2937660512b");
		credentials.put("B091821", "dd29bc3e0efb402bed87127b8275c100");
		credentials.put("B091871", "f39b7989fd06cfd95fc571e3088e2ec0");
		credentials.put("B091886", "f36b3f28b00a406d9718526e9e758bc4");
		credentials.put("B091913", "8db75b8f78142a73bf24f9a6fb4a3ae7");
		credentials.put("B091959", "458fa6f8d9c3c87e65519a608b37d64b");
		credentials.put("B091982", "50934a1cedbf360cd0833903e144d596");
		credentials.put("B091987", "c29f2942fe79be65d1e1bca7ea11baef");
		credentials.put("B092013", "32d3efe482fcbca7d07c5e1c74ba3a4d");
		credentials.put("B092032", "15df0408968f4018cb47aad7703f145");
		credentials.put("B092057", "ad990fdb80775fa215d48dc60cee5bcd");
		credentials.put("B092061", "37d63367c9a90a51fb061b0d7a3ff9e");
		credentials.put("B092158", "f653a8ce913108e0d0f2261be2ae1199");
		credentials.put("B092185", "38467a45015f8910280a914bc6723c5e");
		credentials.put("B092220", "71f63e957d9182b5fd5c15459d3abbf8");
		credentials.put("B092236", "82b5bfd54cbc70f941ecd5d462ab1296");
		credentials.put("B092246", "c0bbcab9032e003063aadce4b759da45");
		credentials.put("B092270", "8ea9bebe5dec707808e2819e61d1089c");
		credentials.put("B092296", "fcedd07cc6b326cbef86e8c314c5b431");
		credentials.put("B092300", "4d7b3762d09339da5de9e6de11f35729");
		credentials.put("B092383", "338d1f4cbcac6a02a8ef6997fbfe856d");
		credentials.put("B092388", "cfc5e794ac914837a0becdbecd76454b");
		credentials.put("B092411", "969e5c1c4c11bb34f15ce0e8f7005d9a");
		credentials.put("B092415", "167ce0db1998d4851188fce2df257e11");
		credentials.put("B092417", "10157936238ee84dd5df4cf99ec0f1f6");
		credentials.put("B092428", "7f838599078ad5fe1b6efde5becec0f0");
		credentials.put("B092451", "73a14b4089276da1d0c009c2b4f99d8a");
		credentials.put("B092473", "b2bc30e7bc3ff4c2882e8d3832c704ad");
		credentials.put("B092503", "f86a7a571ef7bc7710103298a7d0ddd8");
		credentials.put("B092517", "2e5efd06682cf9a721c7f1b1fd93cd00");
		credentials.put("B092526", "7eded0a1815252bd0893034df57618f5");
		credentials.put("B092537", "b25fc112211c47c739fc3a62d1f4b2b0");
		credentials.put("B092555", "5c6ed47bd103aae5fd2ab0a8ba02ecab");
		credentials.put("B092568", "d0a7a003c9f07003efeb5de46af5de22");
		credentials.put("B092624", "f00b2bb127c4aca5506908e645a3d892");
		credentials.put("B092670", "9af2a7173f40ae46a9ad222093e939e");
		credentials.put("B092716", "cc4ce0765c98d0a53b39d95161adc166");
		credentials.put("B092724", "f652a6b9f39369291cdbaa42de0e3339");
		credentials.put("B092750", "20acbfa4af9a38f77662b4cab25ab8c1");
		credentials.put("B092781", "2ccc6c5def29ec94b8e84954130535d");
		credentials.put("B092790", "80f669b77dc4da595d743aed48bcb120");
		credentials.put("B092870", "71a87705b9920d6a5a4829470289510a");
		credentials.put("B092944", "f747f524757d080e9ded73b8e51b794");
		credentials.put("B092980", "78bfe0d6b91abc74145d10a4b1b7c77b");
		credentials.put("B091111", "f121e14af437be7002a5632d0170a855");
		credentials.put("B091202", "475e02e67954a0e6cbb192c4349d652a");
		credentials.put("B091204", "a8294025a51bb017b7b80b21ca05ba19");
		credentials.put("B091359", "d60ad9196d98845e112598abd4d2710c");
		credentials.put("B091386", "1aee1e93274fc8c390325c4b5698d981");
		credentials.put("B091445", "84ae534c7c81a05efd40ea3d2dec2085");
		credentials.put("B091487", "eef51a9eb16a8d033b70de7a3cfd4ba0");
		credentials.put("B091535", "c4050f6ac2a66e0ccc5d51dcb9cfc60c");
		credentials.put("B091566", "83a280c8506b935dabbc376077be8d9f");
		credentials.put("B091591", "c8295db00964a97d29157ea73e0222e3");
		credentials.put("B091599", "2839e367a8f93aab17907f9c1d4cff95");
		credentials.put("B091638", "b06e3e8f4c26d4076d56a05c9e752323");
		credentials.put("B091646", "61721e9194b0b9c5fa4117b65faa601a");
		credentials.put("B091652", "9d03afb8143d2e1de9e9e1e29c2a7d53");
		credentials.put("B091658", "b2648e39f20b700499537f6fae54141c");
		credentials.put("B091675", "a6d620f0a55f90d0b2aca15b00de98f2");
		credentials.put("B091682", "e78614dde12396a6227cea9a9c9e723");
		credentials.put("B091683", "1d76c0a2fcaa45b94619bee87e10da0a");
		credentials.put("B091789", "8f01479650e75a6e0c7a641d76d9083b");
		credentials.put("B091792", "ba7a831df4bfef03cabee913bc4d7f8f");
		credentials.put("B091873", "d4196b860b485ee98a62ae960dc227c0");
		credentials.put("B091900", "925a74b8774e367faf085f70dca9e21f");
		credentials.put("B091915", "495c4825dc34787e65d23366aa163aba");
		credentials.put("B091919", "87c2a7e9c52f792296964e89bb49952f");
		credentials.put("B091970", "43ff298ef8e89ea2ec869de8d332768c");
		credentials.put("B091986", "6f76371d88c6a7473176a59a6ef0fb1f");
		credentials.put("B091990", "be3555e4d0a82cdae1c90c91988734d7");
		credentials.put("B091997", "cae02bcdfe923de1f3834eaa97485364");
		credentials.put("B092001", "64f0f1ad186f616e34b1c14e1f720886");
		credentials.put("B092015", "902879f70984d40fa3daff8b7d4b4f91");
		credentials.put("B092019", "24604340821c6431e3bc9ffae5e151ae");
		credentials.put("B092023", "72c7dae0f845fbeef4fb5967e9f767c2");
		credentials.put("B092035", "fff062ded3f49b36fe1fd62d2e74a5d2");
		credentials.put("B092066", "7a9e6a43db4aae24b914b8707e9125c");
		credentials.put("B092100", "38c6dc9fc95b55117ee16a9d36422297");
		credentials.put("B092131", "78f38c60750212bc0fdc9afd607c2255");
		credentials.put("B092207", "94e7a737bd48e3bba468c20a32737675");
		credentials.put("B092223", "528315352a07fc258ac50d926d455cd2");
		credentials.put("B092232", "b6816783ecddfc1753ffec0826c26f1b");
		credentials.put("B092282", "4c232b0fc33355059828a910922d1dfc");
		credentials.put("B092299", "fcf55ebf30a4cb31eb0ac269cc4f22d0");
		credentials.put("B092302", "9ba624013d6d270beb38aa449e1b6df3");
		credentials.put("B092310", "7f4899020228c6303e5466990ba44110");
		credentials.put("B092334", "2d6f70c23322d7c3ee197d91d447fbe0");
		credentials.put("B092373", "191cbb3c49fb3960c066e2485aebc104");
		credentials.put("B092408", "ddcd1abe085d3b7223c69fb540c21f8a");
		credentials.put("B092414", "a4a85eb64b9442659d49a52a84e5beba");
		credentials.put("B092445", "a046b4693d6c2dbcd147ec9835fb9bb0");
		credentials.put("B092447", "8423896e04d8cc356ddbb29d4cc9288d");
		credentials.put("B092460", "1fe4f313ef592d2a88d85777b0cb6a9c");
		credentials.put("B092493", "c32ce94b3cea14893178853966dc25e1");
		credentials.put("B092507", "95f69f14f53c64abb99612c3ee82433b");
		credentials.put("B092527", "b9c52dcabdfa4355fc5c56ae4f988a12");
		credentials.put("B092557", "11a944d6bd5738615da4983116ea6a7e");
		credentials.put("B092573", "e7b5ecd6df42f3d668eff51fcc0c28d6");
		credentials.put("B092594", "c88829546fa3f820abfb6b3bd5806ea3");
		credentials.put("B092633", "c55614c6f2ac501ce11f14aac475caa1");
		credentials.put("B092702", "4fd9a31d450f1eff7e0037c9c0140a41");
		credentials.put("B092715", "9405557b0ed58f95651717eb89c7c5e6");
		credentials.put("B092725", "9c492c483c28775189179535f9861ffd");
		credentials.put("B092767", "80e0e46821f9c07d8f030dcb99a01331");
		credentials.put("B092788", "14b6b238ed3074ea72697494ffb1dbff");
		credentials.put("B092800", "bfb0529bd29604f3e7319d1f42918582");
		credentials.put("B092845", "b1201e6aacb5450729c7accbffed8c8c");
		credentials.put("B092871", "5e04c97ec6add31846f71364efc21d94");
		credentials.put("B092982", "1f0158c2c9038b7bfa72c7d8b99d4aa8");
		credentials.put("B092986", "ed507861bbb5c3d631fa73ee2448261e");
		credentials.put("B092994", "41a5d52cf121710a836d77192662e338");
		credentials.put("B092995", "6e6f959d7c1c0b9fe18f76e747eca605");
	}

	
}
