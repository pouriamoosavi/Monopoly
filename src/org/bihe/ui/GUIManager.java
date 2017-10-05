package org.bihe.ui;

import org.bihe.ui.actionPanel.ActionPanel;
import org.bihe.ui.actionPanel.ButtonPanel;
import org.bihe.ui.actionPanel.DicePanel;
import org.bihe.ui.actionPanel.PlayerPanel;
import org.bihe.ui.actionPanel.EstatesPanel;
import org.bihe.ui.mainFrame.EnterGamePanel;
import org.bihe.ui.mainFrame.JoinGamePanel;
import org.bihe.ui.mainFrame.LoginPanel;
import org.bihe.ui.mainFrame.MainFrame;
import org.bihe.ui.mainFrame.MakeNewGamePanel;
import org.bihe.ui.mainFrame.SignInPanel;
import org.bihe.ui.mainFrame.SignUpPanel;

public class GUIManager
{
	private static GameFrame gameFrame;
	private static GamePanel gamePanel;
	private static JoinGamePanel joinGamePanel;
	private static LoginPanel loginPanel;
	private static MainFrame mainFrame;
	private static SendRequestFrame sendRequestFrame;
	private static SendRequestPanel sendRequestPanel;
	private static SignInPanel signInPanel;
	private static SignUpPanel signUpPanel;
	private static ActionPanel actionPanel;
	private static ButtonPanel buttonPanel;
	private static DicePanel dicePanel;
	private static PlayerPanel playerPanel;
	private static EstatesPanel estatePanel;
	private static BuyStreetDialog buyStreetDialog;
	private static MakeNewGamePanel makeNewGamePanel;
	private static EnterGamePanel enterGamePanel;
	private static RequestDialog requestDialog;
	private static GetOutOfJailDialog getOutOfJailDialog;

	private GUIManager()
	{
	}

	public static GameFrame getGameFrame()
	{
		if (gameFrame == null)
			gameFrame = new GameFrame();

		return gameFrame;
	}

	public static GamePanel getGamePanel()
	{
		if (gamePanel == null)
			gamePanel = new GamePanel();

		return gamePanel;
	}

	public static JoinGamePanel getJoinGamePanel()
	{
		if (joinGamePanel == null)
			joinGamePanel = new JoinGamePanel();

		return joinGamePanel;
	}

	public static LoginPanel getLoginPanel()
	{
		if (loginPanel == null)
			loginPanel = new LoginPanel();

		return loginPanel;
	}

	public static MainFrame getMainFrame()
	{
		if (mainFrame == null)
			mainFrame = new MainFrame();

		return mainFrame;
	}

	public static SendRequestFrame getSendRequestFrame()
	{
		if (sendRequestFrame == null)
			sendRequestFrame = new SendRequestFrame();

		return sendRequestFrame;
	}

	public static SendRequestPanel getSendRequestPanel()
	{
		if (sendRequestPanel == null)
			sendRequestPanel = new SendRequestPanel();

		return sendRequestPanel;
	}

	public static SignInPanel getSignInPanel()
	{
		if (signInPanel == null)
			signInPanel = new SignInPanel();

		return signInPanel;
	}

	public static SignUpPanel getSignUpPanel()
	{
		if (signUpPanel == null)
			signUpPanel = new SignUpPanel();

		return signUpPanel;
	}

	public static ActionPanel getActionPanel()
	{
		if (actionPanel == null)
			actionPanel = new ActionPanel();

		return actionPanel;
	}

	public static ButtonPanel getButtonPanel()
	{
		if (buttonPanel == null)
			buttonPanel = new ButtonPanel();

		return buttonPanel;
	}

	public static DicePanel getDicePanel()
	{
		if (dicePanel == null)
			dicePanel = new DicePanel();

		return dicePanel;
	}

	public static PlayerPanel getPlayerPanel()
	{
		if (playerPanel == null)
			playerPanel = new PlayerPanel();

		return playerPanel;
	}

	public static EstatesPanel getEstatePanel()
	{
		if (estatePanel == null)
			estatePanel = new EstatesPanel();

		return estatePanel;
	}

	public static BuyStreetDialog getBuyStreetDialog()
	{
		if (buyStreetDialog == null)
			buyStreetDialog = new BuyStreetDialog();

		return buyStreetDialog;
	}

	public static MakeNewGamePanel getMakeNewGamePanel()
	{
		if (makeNewGamePanel == null)
			makeNewGamePanel = new MakeNewGamePanel();

		return makeNewGamePanel;
	}

	public static EnterGamePanel getEnterGamePanel()
	{
		if (enterGamePanel == null)
			enterGamePanel = new EnterGamePanel();

		return enterGamePanel;
	}

	public static RequestDialog getRequestDialog()
	{
		if (requestDialog == null)
			requestDialog = new RequestDialog();

		return requestDialog;
	}

	public static GetOutOfJailDialog getGetOutOfJailDialog()
	{
		if (getOutOfJailDialog == null)
			getOutOfJailDialog = new GetOutOfJailDialog();

		return getOutOfJailDialog;
	}

	// -----------------------GameFrame--------------------------
	public static void addGamePaneltoGameFrame()
	{
		getGameFrame().addComponent(getGamePanel());
	}

	public static void addActionPaneltoGameFrame()
	{
		getGameFrame().addComponent(getActionPanel());
	}

	// ---------------------MainFrame---------------------------
	public static void addLoginPaneltoMainFrame()
	{
		getMainFrame().addComponent(getLoginPanel());
	}

	public static void addJoinGamePaneltoMainFrame()
	{
		getMainFrame().addComponent(getJoinGamePanel());
	}

	public static void addSignInPaneltoMainFrame()
	{
		getMainFrame().addComponent(getSignInPanel());
	}

	public static void addSignUpPaneltoMainFrame()
	{
		getMainFrame().addComponent(getSignUpPanel());
	}

	public static void addMakeNewGamePaneltoMainFrame()
	{
		getMainFrame().addComponent(getMakeNewGamePanel());
	}

	public static void addEnterGamePaneltoMainFrame()
	{
		getMainFrame().addComponent(getEnterGamePanel());
	}
	// ---------------------SendRequestFrame-----------------------

	// -----------------------ActionPanel---------------------------
	public static void addButtonPaneltoActionPanel()
	{
		getActionPanel().addComponent(getButtonPanel());
	}

	public static void addDicePaneltoActionPanel()
	{
		getActionPanel().addComponent(getDicePanel());
	}

	public static void addPlayerPaneltoActionPanel()
	{
		getActionPanel().addComponent(getPlayerPanel());
	}

	public static void addEstatePaneltoActionPanel()
	{
		getActionPanel().addComponent(getEstatePanel());
	}
}